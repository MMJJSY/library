package dw.librarysystem.model;

import dw.librarysystem.dto.ReservationListDto;
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
    private LocalDate expiryDate;
    private StatusR status;
    private int queuePosition;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ReservationListDto toDto(){
        return new ReservationListDto(
                this.reservationId,
                member.getName(),
                book.getTitle(),
                this.queuePosition,
                this.reservationDate,
                this.status
        );
    }
}
