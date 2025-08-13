package dw.librarysystem.model;

import dw.librarysystem.enums.StatusR;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reservation {
    private Long reservationId;
    private Member member;
    private Book book;
    private LocalDate reservationDate;
    private LocalDate expiry_date;
    private StatusR status;
    private int queuePosition;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
