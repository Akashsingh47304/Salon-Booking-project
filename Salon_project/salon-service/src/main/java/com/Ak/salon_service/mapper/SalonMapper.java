package com.Ak.salon_service.mapper;


import com.Ak.salon_service.dto.SalonDto;
import com.Ak.salon_service.model.Salon;


public class SalonMapper {
    public static SalonDto toDto(Salon salon) {

        if (salon == null) {
            return null;
        }

        SalonDto salonDto = new SalonDto();
        salonDto.setName(salon.getName());
        salonDto.setAddress(salon.getAddress());
        salonDto.setCity(salon.getCity());
        salonDto.setEmail(salon.getEmail());
        salonDto.setPhoneNumber(salon.getPhoneNumber());
        salonDto.setOpenTime(salon.getOpenTime());
        salonDto.setCloseTime(salon.getCloseTime());
        salonDto.setImages(salon.getImages());
        salonDto.setOwnerId(salon.getOwnerId());

        return salonDto;
    }


}
