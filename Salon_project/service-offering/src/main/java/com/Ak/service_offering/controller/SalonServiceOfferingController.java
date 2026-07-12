package com.Ak.service_offering.controller;

import com.Ak.service_offering.dto.CategoryDto;
import com.Ak.service_offering.dto.SalonDto;
import com.Ak.service_offering.dto.ServiceDto;
import com.Ak.service_offering.model.ServiceOffering;
import com.Ak.service_offering.services.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@Slf4j
@RequiredArgsConstructor
public class SalonServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;
    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody ServiceDto serviceDto)
             {
            SalonDto salonDto = new SalonDto();
            salonDto.setId(1L); // Set the salon ID to 1 for testing purposes

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(1L); // Set the category ID to 1 for testing purposes`
        log.info("Received request to create service");

        ServiceOffering serviceOffering =
                serviceOfferingService.createService(salonDto, serviceDto, categoryDto);

        return new ResponseEntity<>(serviceOffering, HttpStatus.CREATED);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long serviceId,
            @RequestBody ServiceOffering serviceOffering) {

        log.info("Received request to update service with id={}", serviceId);

        ServiceOffering updatedService =
                serviceOfferingService.updateService(serviceId, serviceOffering);

        return ResponseEntity.ok(updatedService);
    }
}
