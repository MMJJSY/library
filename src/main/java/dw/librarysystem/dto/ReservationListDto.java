package dw.librarysystem.dto;

import dw.librarysystem.enums.StatusR;
import dw.librarysystem.model.Book;
import dw.librarysystem.model.Member;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationListDto {
    private Long reservationId;
    private String memberName;
    private String bookTitle;
    private int queuePosition;
    private LocalDate reservationDate;
    private StatusR status;

}
