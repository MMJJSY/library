package dw.librarysystem.dto;

import dw.librarysystem.enums.MemberType;
import dw.librarysystem.model.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberRegisterDto {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    private String phone;
    @NotBlank(message = "주소를 입력해주세요")
    private String address;
    private MemberType type;

    public Member toEntity() {
        return new Member(
                null,
                this.email,
                this.password,
                this.name,
                this.phone,
                this.address,
                this.type,
                null,
                null
        );
    }
}
