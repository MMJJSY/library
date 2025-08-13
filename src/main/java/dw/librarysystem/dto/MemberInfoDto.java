package dw.librarysystem.dto;

import dw.librarysystem.enums.MemberType;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberInfoDto {

    private Long memberId;
    private String email;
    private String name;
    private String phone;
    private String address;
    private String  type;
    private Timestamp createdAt;
}
