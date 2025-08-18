package dw.librarysystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StatisticPopularDto {
        private Long bookId;
        private String title;
        private String author;
        private int loanCount;

}
