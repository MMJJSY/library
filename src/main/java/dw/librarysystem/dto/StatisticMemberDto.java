package dw.librarysystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatisticMemberDto {
    private int totalLoans;
    private int currentLoans;
    private int overdueLoans;
    private int totalFines;
}
