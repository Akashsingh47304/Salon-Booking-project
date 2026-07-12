package com.Ak.service_offering.services;


import com.Ak.service_offering.dto.CategoryDto;
import com.Ak.service_offering.dto.SalonDto;
import com.Ak.service_offering.dto.ServiceDto;
import com.Ak.service_offering.model.ServiceOffering;


import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering createService(SalonDto salonDto,
                                  ServiceDto serviceDto,
                                  CategoryDto categoryDto);
    ServiceOffering updateService(Long serviceId,ServiceOffering service);
    Set<ServiceOffering> getAllServiceBySalon(Long salonId, Long categoryId);

    Set<ServiceOffering> getServiceByIds(Set<Long> ids);
    ServiceOffering getServiceById(Long id);




}

