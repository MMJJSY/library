package dw.librarysystem.dto;

import dw.librarysystem.enums.Category;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookInfoDto {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Category category;
    private int totalQuantity;
    private int availableQuantity;
    private String location;
}
