package dw.librarysystem.dto;

import dw.librarysystem.enums.Category;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDto {
    private long bookId;
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
}
