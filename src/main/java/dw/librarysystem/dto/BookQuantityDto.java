package dw.librarysystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookQuantityDto {
    private int totalQuantity;
    private int availableQuantity;
}
