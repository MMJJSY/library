package dw.librarysystem.model;

import dw.librarysystem.dto.LoanDto;
import dw.librarysystem.dto.LoanOverdueDto;
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
public class Loan {
    private long loanId;
    private Member member;
    private Book book;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private Status status;
    private BigDecimal fineAmount;
    private Timestamp createdAt;

}
