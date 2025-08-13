package dw.librarysystem.controller;

import dw.librarysystem.dto.LoanBookDto;
import dw.librarysystem.dto.LoanListDto;
import dw.librarysystem.dto.LoanOverdueDto;
import dw.librarysystem.enums.Status;
import dw.librarysystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")

public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanBookDto> loanBook (@RequestParam Long memberId,
                                                 @RequestParam Long bookId){
        return new ResponseEntity<>(
                loanService.loanBook(memberId,bookId),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<LoanListDto>> getLoanListAndSearch(@RequestParam (required = false) Long memberId,
                                                                   @RequestParam (required = false) Status status){
        return new ResponseEntity<>(
                loanService.getLoanListAndSearch(memberId,status),
                HttpStatus.OK
        );
    }
    @PutMapping("/{loanId}/return")
    public ResponseEntity<String> returnLoan(@PathVariable Long loanId) {
        return new ResponseEntity<>(
                loanService.returnLoan(loanId),
                HttpStatus.OK
        );
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<LoanOverdueDto>> overDueLoan(){
        return new ResponseEntity<>(
                loanService.overdueLoan(),
                HttpStatus.OK
        );
    }

}
