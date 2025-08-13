package dw.librarysystem.service;

import dw.librarysystem.dto.MemberInfoDto;
import dw.librarysystem.dto.MemberRegisterDto;
import dw.librarysystem.dto.MemberResponseDto;
import dw.librarysystem.dto.MemberUpdateDto;
import dw.librarysystem.exception.InvalidRequestException;
import dw.librarysystem.mapper.MemberMapper;
import dw.librarysystem.model.Member;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    MemberMapper memberMapper;

    @Transactional
    public MemberResponseDto registerMember(MemberRegisterDto memberRegisterDto){
        Member member = memberMapper.getMemberByEmail(memberRegisterDto.getEmail());
        if (member != null) {
            throw new InvalidRequestException("이미 가입한 이메일입니다.");
        }

        Member memberEntity = memberRegisterDto.toEntity();
        memberEntity.setPassword(passwordEncoder.encode(memberRegisterDto.getPassword()));

        memberMapper.registerMember(memberEntity);
        memberRegisterDto.setPassword(null);

        Member savedMember = memberMapper.getMemberById(memberEntity.getMemberId());

        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setMemberId(savedMember.getMemberId());
        memberResponseDto.setEmail(savedMember.getEmail());
        memberResponseDto.setName(savedMember.getName());
        memberResponseDto.setType(savedMember.getType().name());
        memberResponseDto.setCreatedAt(savedMember.getCreatedAt());

        return memberResponseDto;
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> getAllMembers(int page, int size) {
        int offset = page * size;
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        List<Member> memberList = memberMapper.getAllMembers(offset, size);

        for (Member member : memberList) {
            memberResponseDtoList.add(new MemberResponseDto(
                    member.getMemberId(),
                    member.getEmail(),
                    member.getName(),
                    member.getType().name(),
                    member.getCreatedAt()
            ));
        }
        return  memberResponseDtoList;
    }

    @Transactional(readOnly = true)
    public MemberInfoDto getMemberById(long memberId) {
        Member member = memberMapper.getMemberById(memberId);

        return new MemberInfoDto(
                member.getMemberId(),
                member.getEmail(),
                member.getName(),
                member.getPhone(),
                member.getAddress(),
                member.getType().name(),
                member.getCreatedAt()
        );
    }

    @Transactional
    public String updateMember(MemberUpdateDto memberUpdateDto, long memberId) {

        Member member = memberMapper.getMemberById(memberId);

        member.setName(memberUpdateDto.getName());
        member.setPhone(memberUpdateDto.getPhone());
        member.setAddress(memberUpdateDto.getAddress());
        member.setUpdatedAT(Timestamp.valueOf(LocalDateTime.now()));
        int updateRow = memberMapper.updateMember(member);

        if (updateRow == 0) {
            throw new RuntimeException("회원 정보를 수정하지 못했습니다.");
        }
        return "회원 정보가 수정되었습니다.";
        }
    }

