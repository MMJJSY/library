package dw.librarysystem.dto;

import dw.librarysystem.model.Member;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberResponseDto {
    private long memberId;
    private String email;
    private String name;
    private String type;
    private Timestamp createdAt;

}
