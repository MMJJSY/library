package dw.librarysystem.dto;

import dw.librarysystem.enums.Status;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoanBookDto {
    private Long loanId;
    private Long memberId;
    private Long bookId;
    private Date loanDate;
    private Date dueDate;
    private Status status;
}
