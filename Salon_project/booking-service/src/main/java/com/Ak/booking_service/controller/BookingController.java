package com.Ak.booking_service.controller;


import com.Ak.booking_service.domain.BookingStatus;
import com.Ak.booking_service.dto.*;
import com.Ak.booking_service.mapper.BookingMapper;
import com.Ak.booking_service.model.Booking;
import com.Ak.booking_service.model.SalonReport;
import com.Ak.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
@Slf4j
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam Long salonId
    , @RequestBody BookingRequest bookingRequest){
        UserDto user = new UserDto();
        user.setId(1L); // Hardcoded user ID for demonstration purposes
        SalonDto salon= new SalonDto();
        salon.setId(salonId);
        Set<ServiceDto>serviceDtoSet= new HashSet<>();
        ServiceDto serviceDto= new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setPrice(399);
        serviceDto.setName("Hair cut for men");// Hardcoded service ID for demonstration purposes
        serviceDtoSet.add(serviceDto);
        Booking booking = bookingService.createBooking(bookingRequest, user, salon, serviceDtoSet);
        log.info("Booking created successfully");
        return ResponseEntity.ok(booking);

    }
    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBookingByCustomer(){
        UserDto user = new UserDto();
        user.setId(1L);
      List<Booking> bookings= bookingService.getBookingsByCustomer(user.getId());
      log.info("Bookings retrieved for customerId=1: {}", bookings);
        return ResponseEntity.ok(getBookingDto(bookings));
    }
    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDto>> getBookingBySalon(){

        List<Booking> bookings= bookingService.getBookingsBySalon(1L);
        log.info("Bookings retrieved for salonId=1: {}", bookings);
        return ResponseEntity.ok(getBookingDto(bookings));
    }

    private  Set<BookingDto> getBookingDto(List<Booking> bookings){
        log.info("Get booking dto");
        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.ToDto(booking);
                }).collect(Collectors.toSet());
    }

    @GetMapping("/bookingId")
    public ResponseEntity<BookingDto> getBookingById(
       @PathVariable Long bookingId
    ){

        Booking booking= bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.ToDto(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status
    ){

        Booking booking= bookingService.updateBooking(bookingId,status);
        log.info("Booking status updated successfully for bookingId={} to status={}", bookingId, status);
        return ResponseEntity.ok(BookingMapper.ToDto(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<SlotDto>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date
    ){

        List<Booking> bookings= bookingService.getBookingByDate(date,salonId);
        List<SlotDto> slotDtos = bookings.stream()
                .map(booking->{
                    SlotDto slotDto= new SlotDto();
                    slotDto.setStartTime(booking.getStartTime());
                    slotDto.setEndTime(booking.getEndTime());
                    return slotDto;
                })
                .collect(Collectors.toList());
        log.info("Booked slots: {}", slotDtos);
        return ResponseEntity.ok(slotDtos);
    }
    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport()
    {
       SalonReport salonReport= bookingService.getSalonReport(1L);
       log.info("Salon Report: {}", salonReport);
       return ResponseEntity.ok(salonReport);

    }

}
