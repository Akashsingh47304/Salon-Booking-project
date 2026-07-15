package com.Ak.booking_service.service;


import com.Ak.booking_service.domain.BookingStatus;
import com.Ak.booking_service.dto.BookingRequest;
import com.Ak.booking_service.dto.SalonDto;
import com.Ak.booking_service.dto.ServiceDto;
import com.Ak.booking_service.dto.UserDto;
import com.Ak.booking_service.model.Booking;
import com.Ak.booking_service.model.SalonReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDto userDto, SalonDto salonDto, Set<ServiceDto> serviceDtoSet);

    List<Booking> getBookingsByCustomer(Long customerId);

    List<Booking> getBookingsBySalon(Long salonId);

    Booking getBookingById(Long bookingId);

    Booking updateBooking(Long bookingId, BookingStatus status);
    List<Booking> getBookingByDate(LocalDate date,Long salonId);
SalonReport getSalonReport(Long salonId);

}
