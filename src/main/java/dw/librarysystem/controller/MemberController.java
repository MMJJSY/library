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

    @PostMapping
    public ResponseEntity<MemberResponseDto> registerMember(@Valid @RequestBody MemberRegisterDto memberRegisterDto) {
        MemberResponseDto memberResponseDto = memberService.registerMember(memberRegisterDto);
        return new ResponseEntity<>(memberResponseDto,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers (@RequestParam (defaultValue = "0") int page,
                                                                  @RequestParam (defaultValue = "10")int size) {
        return new ResponseEntity<>(
                memberService.getAllMembers(page, size),
                HttpStatus.OK
        );
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfoDto> getMemberById (@PathVariable long memberId) {
        return new ResponseEntity<>(
                memberService.getMemberById(memberId),
                HttpStatus.OK
        );
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember (@RequestBody MemberUpdateDto memberUpdateDto,
                                                @PathVariable long memberId) {
        return new ResponseEntity<>(
                memberService.updateMember(memberUpdateDto, memberId),
                HttpStatus.OK
        );
    }



}
