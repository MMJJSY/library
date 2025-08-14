package dw.librarysystem.mapper;

import dw.librarysystem.model.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void reservationBook(@Param("reservation") Reservation reservation);
    List<Reservation> reservationByBookId(Long bookId);
    Reservation reservationByMemberIdAndBookId(Long memberId, Long bookId);
    void reservationDate(@Param("reservation") Reservation reservation);
    Reservation reservationByBookIdWithQueuePosition(@Param("bookId")Long bookId);
    void cancelledList (@Param("reservation")Reservation reservation);
    void updateReservationStatus(@Param("reservation") Reservation reservation);
}
