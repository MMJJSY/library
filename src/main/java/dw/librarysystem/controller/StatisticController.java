package dw.librarysystem.controller;


import dw.librarysystem.dto.StatisticMemberDto;
import dw.librarysystem.dto.StatisticPopularDto;
import dw.librarysystem.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    @Autowired
    StatisticService statisticService;

    // GET 요청 (인기 도서 순위 조회)
    // URL : /statistics/popular-books
    @GetMapping("/popular-books")
    public ResponseEntity<List<StatisticPopularDto>> getBookByPopular(@RequestParam(defaultValue = "10") int limit){
        return new ResponseEntity<>(
                statisticService.getBookByPopular(limit),
                HttpStatus.OK
        );
    }

    // GET 요청 (멤버별 대출 통계)
    // URL : /statistics/member-loans/{memberId}
    @GetMapping("/member-loans/{memberId}")
    public ResponseEntity<StatisticMemberDto> getStatisticByMember(@PathVariable Long memberId){
        return new ResponseEntity<>(
                statisticService.getStatisticByMember(memberId),
                HttpStatus.OK
        );
    }
}
