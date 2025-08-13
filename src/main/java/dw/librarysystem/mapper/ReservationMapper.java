package dw.librarysystem.mapper;

import dw.librarysystem.model.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    int reservationBook(Reservation reservation);

    List<Reservation> reservationByBookId(Long bookId);

}
