package com.Ak.booking_service.model;


import lombok.Data;

@Data
public class SalonReport {
    private Long salonId;
    private String salonName;
    private double totalBookings;
    private Double totalRevenue;
    private Double totalRefund;
    private Integer cancelledBookings;
}
