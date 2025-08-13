package dw.librarysystem.service;

import dw.librarysystem.dto.*;
import dw.librarysystem.enums.Category;
import dw.librarysystem.exception.InvalidRequestException;
import dw.librarysystem.exception.ResourceNotFoundException;
import dw.librarysystem.mapper.BookMapper;
import dw.librarysystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    @Transactional
    public String saveBook(BookSaveDto bookSaveDto) {
        Book book = bookMapper.getBookByIsbn(bookSaveDto.getIsbn());
        if (book != null) {
            throw new InvalidRequestException("이미 등록된 도서입니다.");
        }
        Book newBook = new Book(
                null,
                bookSaveDto.getIsbn(),
                bookSaveDto.getTitle(),
                bookSaveDto.getAuthor(),
                bookSaveDto.getPublisher(),
                bookSaveDto.getPublicationYear(),
                bookSaveDto.getCategory(),
                bookSaveDto.getTotalQuantity(),
                bookSaveDto.getTotalQuantity(),
                bookSaveDto.getLocation(),
                null
        );
        if (bookMapper.saveBook(newBook) > 0) {
            return "도서가 등록되었습니다";
        } else {
            return "도서를 등록하지 못했습니다.";
        }
    }

    @Transactional(readOnly = true)
    public List<BookInfoDto> getAllBooksAndSearch(int page, int size, String keyword, Category category) {
        int offset = page * size;
        List<BookInfoDto> bookInfoDto = bookMapper.getAllBooksAndSearch(page, size, keyword, category)
                .stream()
                .map(Book::toInfoDto)
                .toList();
        if (bookInfoDto.getFirst().getId() == null) {
            throw new ResourceNotFoundException("해당 도서를 찾지 못했습니다.");
        }
        return bookInfoDto;
//        List<BookInfoDto> bookInfoDtoList = new ArrayList<>();
//        List<Book> bookList = bookMapper.getAllBooksAndSearch(page,size,keyword,category);
//        for (Book book : bookList) {
//            bookInfoDtoList.add(book.toInfoDto());
//        }
//        return bookInfoDtoList;

    }

    @Transactional(readOnly = true)
    public BookDetailInfoDto getBookById(Long bookId) {
        Book book = bookMapper.getBookById(bookId);
        if (book == null) {
            throw new ResourceNotFoundException("해당 도서가 없습니다. BookId : " + bookId);
        } else {
            return book.toDetailDto();
        }
    }

    @Transactional
    public String updateBook(Long bookId, BookQuantityDto bookQuantityDto) {
        Book book = bookMapper.getBookById(bookId);
        book.setTotalQuantity(bookQuantityDto.getTotalQuantity());
        book.setAvailableQuantity(bookQuantityDto.getAvailableQuantity());

        int updateRow = bookMapper.updateBook(book);
        if (updateRow == 0) {
            throw new InvalidRequestException("수정 실패");

        }
        return "도서 정보가 수정되었습니다.";
    }

    @Transactional
    public String deleteBook(Long bookId) {
        int deleteRow = bookMapper.deleteBook(bookId);
        if (deleteRow > 0) {
            return "도서가 삭제되었습니다.";
        }
        return "도서를 삭제하지 못했습니다.";
    }
}
