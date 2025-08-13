package dw.librarysystem.controller;

import dw.librarysystem.dto.ReservationInfoDto;
import dw.librarysystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationInfoDto> reservationBook(@RequestParam Long memberId,
                                                              @RequestParam Long bookId){
        return new ResponseEntity<>(
                reservationService.reservationBook(memberId, bookId),
                HttpStatus.CREATED
        );
    }
}
