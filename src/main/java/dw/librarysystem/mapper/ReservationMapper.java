package dw.librarysystem.mapper;

import dw.librarysystem.model.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void reservationBook(@Param("reservation") Reservation reservation);
    List<Reservation> getAllReservation(@Param("memberId") Long memberId,
                                        @Param("bookId") Long bookId);
    List<Reservation> reservationByBookId(Long bookId);
    Reservation reservationByMemberIdAndBookId(Long memberId, Long bookId);
    int reservationDate(@Param("reservation") Reservation reservation);
    Reservation reservationByBookIdWithQueuePosition(@Param("bookId")Long bookId);
    List<Reservation> cancelledList ();
    void updateReservationStatus(@Param("reservation") Reservation reservation);
    int deleteReservation(@Param("reservationId")Long reservationId);

}
