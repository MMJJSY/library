package dw.librarysystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE ("대출중"),
    RETURNED ("반납완료"),
    OVERDUE ("연체");

    private final String statusName;


}
