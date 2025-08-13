package dw.librarysystem.model;

import dw.librarysystem.dto.MemberDto;
import dw.librarysystem.enums.MemberType;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private MemberType type;
    private Timestamp createdAt;
    private Timestamp updatedAT;

    public MemberDto toDto(){
        return new MemberDto(
                this.memberId,
                this.email,
                this.password,
                this.name,
                this.phone,
                this.address,
                this.type,
                this.createdAt
        );
    }
}
