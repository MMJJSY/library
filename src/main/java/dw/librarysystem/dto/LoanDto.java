package dw.librarysystem.dto;

import dw.librarysystem.enums.Status;
import dw.librarysystem.model.Book;
import dw.librarysystem.model.Member;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoanDto {
    private long loanId;
    private MemberDto member;
    private BookDto book;
    private Date loanDate;
    private Date dueDate;
    private Date returnDate;
    private Status status;
    private BigDecimal fineAmount;
    private Timestamp createdAt;
}
