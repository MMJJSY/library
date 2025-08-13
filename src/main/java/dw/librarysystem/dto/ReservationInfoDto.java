package dw.librarysystem.dto;

import lombok.*;


import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationInfoDto {
    private Long reservationId;
    private int queuePosition;
    private LocalDate reservationDate;
    private LocalDate expiryDate;
}
