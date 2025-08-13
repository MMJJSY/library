package dw.librarysystem.service;

import dw.librarysystem.dto.ReservationInfoDto;
import dw.librarysystem.exception.InvalidRequestException;
import dw.librarysystem.mapper.BookMapper;
import dw.librarysystem.mapper.ReservationMapper;
import dw.librarysystem.model.Book;
import dw.librarysystem.model.Member;
import dw.librarysystem.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    BookMapper bookMapper;

    public ReservationInfoDto reservationBook(Long memberId,
                                              Long bookId) {
        Book book = bookMapper.getBookById(bookId);
        if (book.getAvailableQuantity() != 0) {
            throw new InvalidRequestException("재고가 남아있습니다. 대출을 이용해주세요");
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
}
