package dw.librarysystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    LITERATURE ("문학"),
    MYSTERY ("추리"),
    APPELLATIVE ("자기계발서"),
    SCIENTIFIC ("과학"),
    HEALTH ("건강"),
    COOKING ("요리"),
    TRAVELING ("여행"),
    HISTORY ("역사");



    private final String categoryName;
}
