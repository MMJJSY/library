package dw.librarysystem.controller;

import dw.librarysystem.dto.ReservationInfoDto;
import dw.librarysystem.dto.ReservationListDto;
import dw.librarysystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    // Post 요청 (도서 예약 정보를 저장)
    @PostMapping
    public ResponseEntity<ReservationInfoDto> reservationBook(@RequestParam Long memberId,
                                                              @RequestParam Long bookId){
        return new ResponseEntity<>(
                reservationService.reservationBook(memberId, bookId),
                HttpStatus.CREATED
        );
    }

    // Get 요청 (예약 목록 조회)
    @GetMapping
    public ResponseEntity<List<ReservationListDto>> getAllReservation(@RequestParam (required = false) Long memberId,
                                                                      @RequestParam (required = false) Long bookId){
        return new ResponseEntity<>(
                reservationService.getAllReservation(memberId, bookId),
                HttpStatus.OK
        );
    }

    // Delete 요청 (예약 취소)
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId){
        return new ResponseEntity<>(
                reservationService.deleteReservation(reservationId),
                HttpStatus.OK
        );
    }

}
