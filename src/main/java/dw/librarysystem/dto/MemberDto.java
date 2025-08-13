package dw.librarysystem.dto;

import dw.librarysystem.enums.MemberType;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDto {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private MemberType type;
    private Timestamp createdAt;
}
