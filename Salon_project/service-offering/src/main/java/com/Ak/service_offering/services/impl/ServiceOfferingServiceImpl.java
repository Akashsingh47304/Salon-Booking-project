package com.Ak.service_offering.services.impl;

import com.Ak.service_offering.dto.CategoryDto;
import com.Ak.service_offering.dto.SalonDto;
import com.Ak.service_offering.dto.ServiceDto;
import com.Ak.service_offering.model.ServiceOffering;
import com.Ak.service_offering.repository.ServiceOfferingRepository;
import com.Ak.service_offering.services.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {
    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDto salonDto,
                                         ServiceDto serviceDto,
                                         CategoryDto categoryDto) {

        log.info("Creating service '{}' for salonId={} and categoryId={}",
                serviceDto.getName(),
                salonDto.getId(),
                categoryDto.getId());

        ServiceOffering serviceOffering = new ServiceOffering();

        serviceOffering.setName(serviceDto.getName());
        serviceOffering.setDescription(serviceDto.getDescription());
        serviceOffering.setImage(serviceDto.getImage());
        serviceOffering.setDuration(serviceDto.getDuration());
        serviceOffering.setSalonId(salonDto.getId());
        serviceOffering.setCategoryId(categoryDto.getId());
        ServiceOffering savedService = serviceOfferingRepository.save(serviceOffering);

        log.info("Service created successfully with id={}", savedService.getId());

        return savedService;
    }

    @Override
    public ServiceOffering updateService(Long serviceId,
                                         ServiceOffering updatedService) {

        log.info("Updating service with id={}", serviceId);

        ServiceOffering existingService = serviceOfferingRepository.findById(serviceId)
                .orElseThrow(() -> {
                    log.error("Service not found with id={}", serviceId);
                    return new RuntimeException("Service not found with id: " + serviceId);
                });

        existingService.setName(updatedService.getName());
        existingService.setDescription(updatedService.getDescription());
        existingService.setPrice(updatedService.getPrice());
        existingService.setDuration(updatedService.getDuration());

        existingService.setCategoryId(updatedService.getCategoryId());


        ServiceOffering savedService = serviceOfferingRepository.save(existingService);

        log.info("Service updated successfully with id={}", savedService.getId());

        return savedService;
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalon(Long salonId,
                                                     Long categoryId) {

        log.info("Fetching services for salonId={} and categoryId={}",
                salonId, categoryId);

        Set<ServiceOffering> services = new HashSet<>(
                serviceOfferingRepository.findBySalonId(salonId)
        );
        if(categoryId!=null) {
            services.stream().filter(service ->service.getCategoryId() != null &&service.getCategoryId().equals(categoryId)).collect(Collectors.toSet());
        }
        log.info("Found {} services", services.size());

        return services;
    }

    @Override
    public Set<ServiceOffering> getServiceByIds(Set<Long> ids) {

        log.info("Fetching services by ids={}", ids);

        Set<ServiceOffering> services = new HashSet<>(
                serviceOfferingRepository.findAllById(ids)
        );

        log.info("Fetched {} services", services.size());

        return services;
    }

    @Override
    public ServiceOffering getServiceById(Long id) {
     ServiceOffering service = serviceOfferingRepository.findById(id)
             .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
     log.info("fetched the service by id {}",id);
     return service;
    }
}

