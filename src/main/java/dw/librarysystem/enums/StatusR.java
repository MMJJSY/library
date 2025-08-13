package dw.librarysystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusR {
    WAITING("예약 대기중"),
    ACTIVE("예약 중"),
    CANCELLED("예약 취소됨"),
    FULFILLED("대출 완료");

    private final String statusName;
}
