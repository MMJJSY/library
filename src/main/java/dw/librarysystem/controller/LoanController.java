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

    // Post 요청 (도서 대출 정보를 저장)
    @PostMapping
    public ResponseEntity<LoanBookDto> loanBook (@RequestParam Long memberId,
                                                 @RequestParam Long bookId){
        return new ResponseEntity<>(
                loanService.loanBook(memberId,bookId),
                HttpStatus.OK
        );
    }
    // Get 요청 (대출 목록 조회 / memberId와 상태로 검색 가능)
    @GetMapping
    public ResponseEntity<List<LoanListDto>> getLoanListAndSearch(@RequestParam (required = false) Long memberId,
                                                                   @RequestParam (required = false) Status status){
        return new ResponseEntity<>(
                loanService.getLoanListAndSearch(memberId,status),
                HttpStatus.OK
        );
    }

    // Put 요청 (대출 도서의 반납을 처리)
    @PutMapping("/{loanId}/return")
    public ResponseEntity<String> returnLoan(@PathVariable Long loanId) {
        return new ResponseEntity<>(
                loanService.returnLoan(loanId),
                HttpStatus.OK
        );
    }

    // Get 요청 (도서 납기일이 지난 연체도서를 조회)
    @GetMapping("/overdue")
    public ResponseEntity<List<LoanOverdueDto>> overDueLoan(){
        return new ResponseEntity<>(
                loanService.overdueLoan(),
                HttpStatus.OK
        );
    }

}
