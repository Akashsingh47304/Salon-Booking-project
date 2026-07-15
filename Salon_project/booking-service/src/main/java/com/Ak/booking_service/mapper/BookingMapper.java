package com.Ak.booking_service.mapper;

import com.Ak.booking_service.dto.BookingDto;
import com.Ak.booking_service.model.Booking;

public class BookingMapper {
    public static BookingDto ToDto(Booking booking){
        BookingDto bookingDto= new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setStartTime(booking.getStartTime());
        bookingDto.setEndTime(booking.getEndTime());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setSalonId(booking.getSalonId());
        bookingDto.setCustomerId(booking.getCustomerId());
        bookingDto.setServiceIds(booking.getServiceIds());
        return bookingDto;


    }
}
