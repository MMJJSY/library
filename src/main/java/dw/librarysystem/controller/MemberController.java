package dw.librarysystem.controller;

import dw.librarysystem.dto.MemberInfoDto;
import dw.librarysystem.dto.MemberRegisterDto;
import dw.librarysystem.dto.MemberResponseDto;
import dw.librarysystem.dto.MemberUpdateDto;
import dw.librarysystem.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    // Post 요청 (새로운 회원 등록)
    @PostMapping
    public ResponseEntity<MemberResponseDto> registerMember(@Valid @RequestBody MemberRegisterDto memberRegisterDto) {
        MemberResponseDto memberResponseDto = memberService.registerMember(memberRegisterDto);
        return new ResponseEntity<>(memberResponseDto,
                HttpStatus.CREATED
        );
    }
    // Get 요청 (모든 회원 조회)
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers (@RequestParam (defaultValue = "0") int page,
                                                                  @RequestParam (defaultValue = "10")int size) {
        return new ResponseEntity<>(
                memberService.getAllMembers(page, size),
                HttpStatus.OK
        );
    }
    // Get 요청 (회원 ID로 회원 조회)
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfoDto> getMemberById (@PathVariable long memberId) {
        return new ResponseEntity<>(
                memberService.getMemberById(memberId),
                HttpStatus.OK
        );
    }

    // Put 요청 (회원 정보 수정)
    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember (@RequestBody MemberUpdateDto memberUpdateDto,
                                                @PathVariable long memberId) {
        return new ResponseEntity<>(
                memberService.updateMember(memberUpdateDto, memberId),
                HttpStatus.OK
        );
    }



}
