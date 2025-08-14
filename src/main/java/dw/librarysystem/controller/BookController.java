package dw.librarysystem.controller;

import dw.librarysystem.dto.*;
import dw.librarysystem.enums.Category;
import dw.librarysystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    BookService bookService;

    // Post 요청 (새로운 책 저장)
    @PostMapping
    public ResponseEntity<String> saveBook (@RequestBody BookSaveDto bookSaveDto) {
        return new ResponseEntity<>(
                bookService.saveBook(bookSaveDto),
                HttpStatus.CREATED
        );
    }

    // Get 요청 (모든 도서의 목록과 제목/저자/카테고리로 검색)
    @GetMapping
    public ResponseEntity<List<BookInfoDto>> getAllBooksAndSearch(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(required = false)String keyword,
                                                          @RequestParam(required = false)Category category) {
        return new ResponseEntity<>(
                bookService.getAllBooksAndSearch(page,size,keyword,category),
                HttpStatus.OK
        );
    }
    // Get 요청 (도서 번호로 책의 상세정보를 조회)
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDetailInfoDto> getBookById (@PathVariable Long bookId){
        return new ResponseEntity<>(
                bookService.getBookById(bookId),
                HttpStatus.OK
        );
    }

    // Put 요청 (도서의 총 수량/대출가능 재고 업데이트)
    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook (@PathVariable Long bookId,
                                              @RequestBody BookQuantityDto bookQuantityDto) {
        return new ResponseEntity<>(
                bookService.updateBook(bookId, bookQuantityDto),
                HttpStatus.OK
        );
    }
    // Delete 요청 (도서 삭제)
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook (@PathVariable Long bookId){
        return new ResponseEntity<>(
                bookService.deleteBook(bookId),
                HttpStatus.OK
        );
    }

}
