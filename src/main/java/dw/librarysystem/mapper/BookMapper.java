package dw.librarysystem.mapper;

import dw.librarysystem.enums.Category;
import dw.librarysystem.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    Book getBookByIsbn(@Param("isbn")String isbn);
    int saveBook(@Param("book") Book book);
    List<Book> getAllBooksAndSearch(@Param("offset") int offset,
                           @Param("limit") int limit,
                           @Param("keyword") String keyword,
                           @Param("category") Category category);
    Book getBookById(@Param("bookId")Long bookId);
    int updateBook(@Param("book")Book book);
    void updateAvailableQuantity(@Param("book")Book book);
    int deleteBook(@Param("bookId")Long bookId);


}
