package com.Ak.salon_service.service;

import com.Ak.salon_service.dto.SalonDto;
import com.Ak.salon_service.dto.UserDto;
import com.Ak.salon_service.model.Salon;

import java.util.List;

public  interface SalonService {

    Salon createSalon(SalonDto salon, UserDto user);
    Salon updateSalon(SalonDto salon,UserDto user,Long salonId);
    List<Salon> getAllSalons();
    Salon getSalonById(Long salonId);
    Salon getSalonByOwnerId(Long ownerId);
    List<Salon> searchSalonByCity(String city);
}
