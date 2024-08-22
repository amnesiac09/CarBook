package aleksandrenaneishvili.carbook.service;

import aleksandrenaneishvili.carbook.dto.ServiceForCarDto;

import java.util.List;

public interface ServiceForCarService {
  ServiceForCarDto add(ServiceForCarDto serviceDto);
  List<ServiceForCarDto> getAllServicesForCar(Long carId);
}
