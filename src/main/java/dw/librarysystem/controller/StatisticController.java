package dw.librarysystem.controller;


import dw.librarysystem.dto.StatisticPopularDto;
import dw.librarysystem.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
