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

import java.util.Set;

@RestController
@RequestMapping("/api/serviceoffering")
@RequiredArgsConstructor
@Slf4j
public class ServiceController {
    private final ServiceOfferingService serviceOfferingService;
    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody SalonDto salonDto,
            @RequestBody ServiceDto serviceDto,
            @RequestBody CategoryDto categoryDto) {

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

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalon(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId) {

        log.info("Fetching services for salonId={} and categoryId={}", salonId, categoryId);

        Set<ServiceOffering> services =
                serviceOfferingService.getAllServiceBySalon(salonId, categoryId);

        return ResponseEntity.ok(services);
    }

    @PostMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> ids) {

        log.info("Fetching services by ids={}", ids);

        Set<ServiceOffering> services =
                serviceOfferingService.getServiceByIds(ids);

        return ResponseEntity.ok(services);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long id){
        log.info("Fetching service by id={}", id);
        ServiceOffering service = serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(service);
    }
}
