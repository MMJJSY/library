package dw.librarysystem.mapper;

import dw.librarysystem.enums.Status;
import dw.librarysystem.model.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoanMapper {


    Loan getLoanById(@Param("loanId")Long loanId);
    Loan getLoanByMemberAndBook(@Param("memberId")Long memberId,
                     @Param("bookId")Long bookId);
    void loanBook(@Param("loan")Loan loan);
    List<Loan> getLoanByMemberId(@Param("memberId")Long memberId);
    List<Loan> getLoanListAndSearch(@Param("memberId")Long memberId,
                                    @Param("status")Status status);
    int returnLoan(@Param("loan") Loan loan);
    void overdueStatus(@Param("loan")Loan loan);
    List<Loan> overdueLoan();
}
