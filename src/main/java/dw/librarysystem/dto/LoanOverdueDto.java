package dw.librarysystem.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoanOverdueDto {

    private Long loanId;
    private String memberName;
    private String bookTitle;
    private Date dueDate;
    private long overdueDays;
    private BigDecimal fineAmount;
}
