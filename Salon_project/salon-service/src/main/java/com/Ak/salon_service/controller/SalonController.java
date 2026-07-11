package com.Ak.salon_service.controller;

import com.Ak.salon_service.dto.SalonDto;
import com.Ak.salon_service.dto.UserDto;
import com.Ak.salon_service.mapper.SalonMapper;
import com.Ak.salon_service.model.Salon;
import com.Ak.salon_service.service.SalonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@Slf4j
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDto> createSalon(@RequestBody SalonDto salonDto){
        UserDto userDto=new UserDto();
        userDto.setId(1L);

        Salon salon =salonService.createSalon(salonDto,userDto);
        SalonDto salonDto1= SalonMapper.toDto(salon);
        return ResponseEntity.ok(salonDto1);

    }
    @PutMapping("/{salonId}")
    public ResponseEntity<SalonDto> updateSalon(@PathVariable Long salonId,
            @RequestBody SalonDto salonDto){
        UserDto userDto=new UserDto();
        userDto.setId(1L);

        Salon salon =salonService.updateSalon(salonDto,userDto,salonId);
        SalonDto salonDto1= SalonMapper.toDto(salon);
        return ResponseEntity.ok(salonDto1);

    }
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long salonId){
        Salon salon =salonService.getSalonById(salonId);
        SalonDto salonDto1= SalonMapper.toDto(salon);
        return ResponseEntity.ok(salonDto1);

    }

    @GetMapping
    public ResponseEntity<List<SalonDto>> getSalons(){
       List<Salon>  salons =salonService.getAllSalons();
      List<SalonDto> salonDtos= salons
              .stream().map(SalonMapper::toDto).toList();
        return ResponseEntity.ok().body(salonDtos);

    }
    @GetMapping("/search")
    public ResponseEntity<List<SalonDto>> searchSalons(@RequestParam("city") String city) {

        List<Salon> salons = salonService.searchSalonByCity(city);

        List<SalonDto> salonDtos = salons.stream()
                .map(SalonMapper::toDto)
                .toList();

        return ResponseEntity.ok(salonDtos);
    }
    @GetMapping("/owner")
    public ResponseEntity<SalonDto> getSalonsByOwnerId(){
        UserDto userDto = new UserDto();
        userDto.setId(1L);
       Salon salon = salonService.getSalonByOwnerId(userDto.getId());
        SalonDto salonDto= SalonMapper.toDto(salon);
       return ResponseEntity.ok(salonDto);

    }
}
