package dw.librarysystem.mapper;

import dw.librarysystem.dto.MemberDto;
import dw.librarysystem.dto.MemberRegisterDto;
import dw.librarysystem.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    Member getMemberByEmail (@Param("email") String email);
    void registerMember (Member member);
    List<Member> getAllMembers (@Param("offset") int offset,
                                @Param("limit") int limit);
    Member getMemberById (@Param("memberId") long memberId);
    int updateMember (@Param("member") Member member);
}
