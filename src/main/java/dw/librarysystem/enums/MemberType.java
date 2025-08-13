package dw.librarysystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberType {
    STUDENT("학생"),
    TEACHER("교직원"),
    GENERAL("일반인");


    private final String memberName;

}
