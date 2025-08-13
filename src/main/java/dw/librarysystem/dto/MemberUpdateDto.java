package dw.librarysystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberUpdateDto {
    private String name;
    private String phone;
    private String address;
}
