package com.Ak.booking_service.service.impl;

import com.Ak.booking_service.domain.BookingStatus;
import com.Ak.booking_service.dto.BookingRequest;
import com.Ak.booking_service.dto.SalonDto;
import com.Ak.booking_service.dto.ServiceDto;
import com.Ak.booking_service.dto.UserDto;
import com.Ak.booking_service.model.Booking;
import com.Ak.booking_service.model.SalonReport;
import com.Ak.booking_service.repository.BookingRepository;
import com.Ak.booking_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest request,
                                 UserDto userDto,
                                 SalonDto salonDto,
                                 Set<ServiceDto> serviceDtoSet) {
        int totalDuration=serviceDtoSet.stream()
                .mapToInt(ServiceDto::getDuration)
                .sum();
        LocalDateTime startTime=request.getStartTime();
        LocalDateTime endTime=startTime.plusMinutes(totalDuration);
        Boolean isSlotAvailable=isTimeSlotAvailable(salonDto, startTime, endTime);
        int totalPrice=serviceDtoSet.stream()
                .mapToInt(ServiceDto::getPrice)
                .sum();
        Set<Long> idList=serviceDtoSet.stream()
                .map(ServiceDto::getId)
                .collect(Collectors.toSet());




        log.info("Creating booking for customerId={} at salonId={}",
                userDto.getId(), salonDto.getId());

        Booking booking = new Booking();

        booking.setCustomerId(userDto.getId());
        booking.setSalonId(salonDto.getId());
        booking.setStatus(BookingStatus.PENDING);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setServiceIds(idList);
        booking.setTotalPrice(totalPrice);
        booking.setBookingDate(startTime.toLocalDate());
        Booking savedBooking = bookingRepository.save(booking);
        log.info("Booking created successfully with id={}", savedBooking.getId());

        return savedBooking;
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDto,
                                       LocalDateTime startTime,
                                       LocalDateTime endTime) {

        log.info("Checking availability for salonId={} from {} to {}",
                salonDto.getId(), startTime, endTime);

        List<Booking> existedBookings = getBookingsBySalon(salonDto.getId());

        LocalDateTime salonOpenTime =
                salonDto.getOpenTime().atDate(startTime.toLocalDate());

        LocalDateTime salonCloseTime =
                salonDto.getCloseTime().atDate(startTime.toLocalDate());

        // Check salon operating hours
        if (startTime.isBefore(salonOpenTime) || endTime.isAfter(salonCloseTime)) {

            log.warn("Requested slot {} - {} is outside salon operating hours.",
                    startTime, endTime);

            throw new RuntimeException("Booking time is outside salon operating hours.");
        }

        // Check for overlapping bookings
        for (Booking existingBooking : existedBookings) {

            if (startTime.isBefore(existingBooking.getEndTime())
                    && endTime.isAfter(existingBooking.getStartTime())) {

                log.warn("Requested slot overlaps with booking id={}",
                        existingBooking.getId());

                throw new RuntimeException("Selected time slot is already booked.");
            }
            if(startTime.isEqual(existingBooking.getStartTime()) || endTime.isEqual(existingBooking.getEndTime())){
                log.warn("Requested slot overlaps with booking id={}",
                        existingBooking.getId());

                throw new RuntimeException("Selected time slot is already booked.");
            }
        }

        log.info("Time slot is available.");

        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {

        log.info("Fetching bookings for customerId={}", customerId);

        List<Booking> bookings = bookingRepository.findByCustomerId(customerId);

        log.info("Found {} bookings for customerId={}", bookings.size(), customerId);

        return bookings;
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {

        log.info("Fetching bookings for salonId={}", salonId);

        List<Booking> bookings = bookingRepository.findBySalonId(salonId);

        log.info("Found {} bookings for salonId={}", bookings.size(), salonId);

        return bookings;
    }

    @Override
    public Booking getBookingById(Long bookingId) {

        log.info("Fetching booking with id={}", bookingId);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> {
                    log.error("Booking not found with id={}", bookingId);
                    return new RuntimeException("Booking not found with id: " + bookingId);
                });

        log.info("Booking fetched successfully.");

        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {

        log.info("Updating booking id={} with status={}", bookingId, status);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> {
                    log.error("Booking not found with id={}", bookingId);
                    return new RuntimeException("Booking not found with id: " + bookingId);
                });

        booking.setStatus(status);

        Booking updatedBooking = bookingRepository.save(booking);

        log.info("Booking updated successfully.");

        return updatedBooking;
    }

    @Override
    public List<Booking> getBookingByDate(LocalDate date, Long salonId) {

        log.info("Fetching bookings for salonId={} on date={}", salonId, date);
        List<Booking> bookingsOnDate= new ArrayList<>();
        List<Booking> allBookings=getBookingsBySalon(salonId);
        if(date==null){
            return allBookings;
        }else{
             bookingsOnDate =  allBookings.stream()
                    .filter(b -> b.getStartTime().toLocalDate().equals(date))
                    .collect(Collectors.toList());
        }

        log.info("Found {} bookings.", bookingsOnDate.size());

        return bookingsOnDate;
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {

        log.info("Generating salon report for salonId={}", salonId);

        List<Booking> bookings = bookingRepository.findBySalonId(salonId);


        SalonReport report = new SalonReport();

        report.setSalonId(salonId);
        report.setTotalBookings(bookings.size());

        double totalRevenue = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CONFIRMED)
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        report.setTotalRevenue(totalRevenue);


        List<Booking> cancelledBookings = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.CANCELLED)
                .collect(Collectors.toList());

        Double totalRefund = cancelledBookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();

        long pending = bookings.stream()
                .filter(b -> b.getStatus() == BookingStatus.PENDING)
                .count();

        report.setCancelledBookings(cancelledBookings.size());
        report.setSalonId(salonId);
        report.setTotalRefund(totalRefund);
        report.setTotalBookings(bookings.size());
        report.setTotalRevenue(totalRevenue);
      //  report.setSalonName(sa);

        log.info("Salon report generated successfully for salonId={}", salonId);

        return report;
    }
}
