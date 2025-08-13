package dw.librarysystem.service;

import dw.librarysystem.dto.ReservationInfoDto;
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

    public ReservationInfoDto reservationBook(Long memberId,
                                              Long bookId) {

        int queuePosition;
        List<Reservation> reservationList = reservationMapper.reservationByBookId(bookId);
        queuePosition = reservationList.size() + 1;

        Reservation reservation = new Reservation();
        Member member = new Member();
        member.setMemberId(memberId);
        reservation.setMember(member);

        Book book = new Book();
        book.setBookId(bookId);
        reservation.setBook(book);
        reservation.setQueuePosition(queuePosition);

        reservationMapper.reservationBook(reservation);


        return null;
    }
}
