package dw.librarysystem.mapper;

import dw.librarysystem.model.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void reservationBook(@Param("reservation") Reservation reservation);
    List<Reservation> reservationByBookId(Long bookId);
    void reservationDate(@Param("reservation") Reservation reservation);

}
