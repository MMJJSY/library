package dw.librarysystem.service;

import dw.librarysystem.dto.ReservationInfoDto;
import dw.librarysystem.dto.ReservationListDto;
import dw.librarysystem.enums.Status;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    LoanMapper loanMapper;

    public ReservationInfoDto reservationBook(Long memberId,
                                              Long bookId) {
        Book book = bookMapper.getBookById(bookId);
        if (book.getAvailableQuantity() != 0) {
            throw new InvalidRequestException("재고가 남아있습니다. 대출을 이용해주세요");
        }
        List<Loan> overdueCheck = loanMapper.getLoanByMemberId(memberId);
        for (Loan loan : overdueCheck) {
            if (loan != null &&loan.getStatus() == Status.OVERDUE) {
                throw new InvalidRequestException("연체 중에는 예약할 수 없습니다.");
            }
        }

            int queuePosition;
            List<Reservation> reservationList = reservationMapper.reservationByBookId(bookId);
            queuePosition = reservationList.size() + 1;

            Reservation reservation = new Reservation();
            Member member = new Member();
            member.setMemberId(memberId);
            reservation.setMember(member);

            reservation.setBook(book);
            reservation.setQueuePosition(queuePosition);

            reservationMapper.reservationBook(reservation);

            return new ReservationInfoDto(memberId, queuePosition);
        }
    public List<ReservationListDto> getAllReservation(Long memberId, Long bookId) {

        List<ReservationListDto> reservationListDtoList = new ArrayList<>();
        List<Reservation> reservationList = reservationMapper.getAllReservation(memberId, bookId);
        for (Reservation reservation : reservationList) {
            reservationListDtoList.add(reservation.toDto());
        }
        return reservationListDtoList;
    }

    public String deleteReservation(Long reservationId) {
        int deleteRow = reservationMapper.deleteReservation(reservationId);
        if (deleteRow < 0) {
            return "예약 취소 실패";
        }
        return "예약이 취소되었습니다.";
    }
}
