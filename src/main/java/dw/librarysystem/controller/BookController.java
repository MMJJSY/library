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

    @PostMapping
    public ResponseEntity<String> saveBook (@RequestBody BookSaveDto bookSaveDto) {
        return new ResponseEntity<>(
                bookService.saveBook(bookSaveDto),
                HttpStatus.CREATED
        );
    }

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
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDetailInfoDto> getBookById (@PathVariable Long bookId){
        return new ResponseEntity<>(
                bookService.getBookById(bookId),
                HttpStatus.OK
        );
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook (@PathVariable Long bookId,
                                              @RequestBody BookQuantityDto bookQuantityDto) {
        return new ResponseEntity<>(
                bookService.updateBook(bookId, bookQuantityDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook (@PathVariable Long bookId){
        return new ResponseEntity<>(
                bookService.deleteBook(bookId),
                HttpStatus.OK
        );
    }

}
