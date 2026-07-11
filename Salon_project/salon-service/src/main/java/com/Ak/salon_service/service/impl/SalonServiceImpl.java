package com.Ak.salon_service.service.impl;

import com.Ak.salon_service.dto.SalonDto;
import com.Ak.salon_service.dto.UserDto;
import com.Ak.salon_service.model.Salon;
import com.Ak.salon_service.repository.SalonRepository;
import com.Ak.salon_service.service.SalonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {
    private final SalonRepository salonRepository;
    @Override
    public Salon createSalon(SalonDto req, UserDto user) {
        Salon salon= toSalon(req,user);
        return salonRepository.save(salon);

    }

    @Override
    public Salon updateSalon(SalonDto salon, UserDto user, Long salonId) {

        Salon existingSalon = salonRepository.findById(salonId)
                .orElseThrow(() ->
                        new RuntimeException("Salon not found with id: " + salonId));

        // Check if the logged-in user owns this salon
        if (!existingSalon.getOwnerId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to update this salon.");
        }

        existingSalon.setName(salon.getName());
        existingSalon.setCity(salon.getCity());
        existingSalon.setAddress(salon.getAddress());
        existingSalon.setEmail(salon.getEmail());
        existingSalon.setPhoneNumber(salon.getPhoneNumber());
        existingSalon.setImages(salon.getImages());
        existingSalon.setOpenTime(salon.getOpenTime());
        existingSalon.setCloseTime(salon.getCloseTime());

        // Don't update ownerId. It should remain the original owner.
        return salonRepository.save(existingSalon);
    }

    @Override
    public List<Salon> getAllSalons() {
       return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
       Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new RuntimeException("Salon not found with id: " + salonId));
       return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId)
                .orElseThrow(() -> new RuntimeException("Salon not found for owner id: " + ownerId));
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
    private Salon toSalon(SalonDto req,UserDto user ){
        Salon salon=Salon.builder()
                .name(req.getName())
                .images(req.getImages())
                .address(req.getAddress())
                .phoneNumber(req.getPhoneNumber())
                .email(req.getEmail())
                .city(req.getCity())
                .ownerId(user.getId())
                .closeTime(req.getCloseTime())
                .openTime(req.getOpenTime())
                .build();
        return salon;
    }
}


