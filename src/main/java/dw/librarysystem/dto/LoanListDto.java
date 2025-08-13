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
public class LoanListDto {
    private long loanId;
    private String memberName;
    private String bookTitle;
    private Date loanDate;
    private Date dueDate;
    private Status status;
}
