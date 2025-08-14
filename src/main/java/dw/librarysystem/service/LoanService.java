package dw.librarysystem.service;

import dw.librarysystem.dto.*;
import dw.librarysystem.enums.Status;
import dw.librarysystem.enums.StatusR;
import dw.librarysystem.exception.InvalidRequestException;
import dw.librarysystem.mapper.BookMapper;
import dw.librarysystem.mapper.LoanMapper;
import dw.librarysystem.mapper.ReservationMapper;
import dw.librarysystem.model.Book;
import dw.librarysystem.model.Loan;
import dw.librarysystem.model.Member;
import dw.librarysystem.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanMapper loanMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    ReservationMapper reservationMapper;

    @Transactional
    public LoanBookDto loanBook(Long memberId,
                                Long bookId) {
        Loan checkLoan = loanMapper.getLoanByMemberAndBook(memberId, bookId);
        if (checkLoan != null
                && (checkLoan.getStatus() == Status.ACTIVE || checkLoan.getStatus() == Status.OVERDUE )){
            throw new InvalidRequestException("이미 대출중인 도서입니다.");
        }
        if(bookMapper.getBookById(bookId).getAvailableQuantity() <= 0){
            throw new InvalidRequestException("현재 재고가 없으니 예약을 이용해주세요.");
        }
        List<Loan> loanList = loanMapper.getLoanByMemberId(memberId);
        for (Loan loan : loanList) {
            if (loan.getStatus() == Status.OVERDUE ) {
                throw new InvalidRequestException("연체중에는 다른 도서를 빌릴 수 없습니다");
            }
        }
        List<Reservation> reservationList = reservationMapper.reservationByBookId(bookId);
        for (Reservation reservation : reservationList) {
            if (reservation != null && reservation.getStatus() == StatusR.ACTIVE &&
                !reservation.getMember().getMemberId().equals(memberId)){
                throw new InvalidRequestException("예약 중인 도서이므로 대출이 불가능 합니다. 예약을 이용해주세요.");
            }
        }

        Loan loan = new Loan();

        Member member = new Member();
        member.setMemberId(memberId);
        loan.setMember(member);

        Book book = bookMapper.getBookById(bookId);
        loan.setBook(book);


        loan.setDueDate(Date.valueOf(LocalDate.now().plusDays(14)));

        loanMapper.loanBook(loan);

        book.setAvailableQuantity(book.getAvailableQuantity()-1);
        bookMapper.updateAvailableQuantity(book);
        
        Reservation reservation = reservationMapper.reservationByMemberIdAndBookId(memberId, bookId);
        if (reservation != null && reservation.getStatus() == StatusR.ACTIVE) {
            reservation.setStatus(StatusR.FULFILLED);
            reservationMapper.updateReservationStatus(reservation);
        }

//        Reservation reservation = reservationMapper.reservationByMemberIdAndBookId(memberId, bookId);
//        if (reservation != null && reservation.getStatus() == StatusR.ACTIVE) {
//            reservation.setStatus(StatusR.FULFILLED);
//            reservationMapper.updateReservationStatus(reservation);
//        }


        Loan savedLoan = loanMapper.getLoanByMemberAndBook(memberId, bookId);

        return new LoanBookDto(
                savedLoan.getLoanId(),
                savedLoan.getMember().getMemberId(),
                savedLoan.getBook().getBookId(),
                savedLoan.getLoanDate(),
                savedLoan.getDueDate(),
                savedLoan.getStatus()
        );
    }

    @Transactional(readOnly = true)
    public List<LoanListDto> getLoanListAndSearch(Long memberId, Status status) {

        return loanMapper.getLoanListAndSearch(memberId, status).stream().map(loan -> new LoanListDto(
                loan.getLoanId(),
                loan.getMember().getName(),
                loan.getBook().getTitle(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getStatus()
        )).toList();
    }

    @Transactional
    public String returnLoan(Long loanId) {


        Loan loan = loanMapper.getLoanById(loanId);
        if (loan == null) {
            throw new InvalidRequestException("존재하지 않는 대출 기록입니다. loanId : "+loanId);
        }
        if (loan.getStatus() == Status.RETURNED) {
            throw new InvalidRequestException("이미 반납한 기록이 있습니다. loanId : " + loanId);
        }
        loan.setReturnDate(Date.valueOf(LocalDate.now()));
        loan.setStatus(Status.RETURNED);

        int updateRow = loanMapper.returnLoan(loan);
        if (updateRow == 0) {
            throw new InvalidRequestException("반납 실패");
        }

        Book book = bookMapper.getBookById(loan.getBook().getBookId());
        book.setAvailableQuantity(book.getAvailableQuantity()+1);
        bookMapper.updateAvailableQuantity(book);

        Reservation reservation = reservationMapper.reservationByBookIdWithQueuePosition(loan.getBook().getBookId());
        System.out.println(reservation);
        System.out.println();
        if(reservation != null) {
            reservation.setReservationDate(LocalDate.now());
            reservation.setExpiryDate(LocalDate.now().plusDays(3));
            reservation.setStatus(StatusR.ACTIVE);
            System.out.println(reservation);
            System.out.println();
            reservationMapper.reservationDate(reservation);
        }

        return ("도서가 반납되었습니다.");
    }

    @Transactional
    public List<LoanOverdueDto> overdueLoan() {
        List<LoanOverdueDto> overdueDtoList = new ArrayList<>();
        List<Loan> loanList = loanMapper.overdueLoan();


        for (Loan loan : loanList) {
            if (loan.getStatus() != Status.RETURNED) {
                LocalDate overdueDate = loan.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDay = LocalDate.now();

                long overdueDays = ChronoUnit.DAYS.between(overdueDate, toDay);
                BigDecimal fineAmount = BigDecimal.valueOf(overdueDays).multiply(BigDecimal.valueOf(100));
                if (overdueDays > 0) {
                    loan.setStatus(Status.OVERDUE);
                    loan.setFineAmount(fineAmount);
                    loanMapper.overdueStatus(loan);
                }


                LoanOverdueDto loanOverdueDto = new LoanOverdueDto(
                        loan.getLoanId(),
                        loan.getMember().getName(),
                        loan.getBook().getTitle(),
                        loan.getDueDate(),
                        overdueDays,
                        fineAmount
                );

                overdueDtoList.add(loanOverdueDto);
            }
        }
        return overdueDtoList;
    }
}
