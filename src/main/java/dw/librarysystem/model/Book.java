package dw.librarysystem.model;

import dw.librarysystem.dto.BookDetailInfoDto;
import dw.librarysystem.dto.BookDto;
import dw.librarysystem.dto.BookInfoDto;
import dw.librarysystem.enums.Category;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private Long bookId;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private Category category;
    private int totalQuantity;
    private int availableQuantity;
    private String location;
    private Timestamp createdAt;

    public BookInfoDto toInfoDto() {
        return new BookInfoDto(
                this.bookId,
                this.isbn,
                this.title,
                this.author,
                this.publisher,
                this.category,
                this.totalQuantity,
                this.availableQuantity,
                this.location
        );
    }

    public BookDetailInfoDto toDetailDto() {
        return new BookDetailInfoDto(
                this.bookId,
                this.isbn,
                this.title,
                this.author,
                this.publisher,
                this.publicationYear,
                this.category,
                this.totalQuantity,
                this.availableQuantity,
                this.location
        );

    }
}

