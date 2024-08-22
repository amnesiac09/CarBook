package aleksandrenaneishvili.carbook.impl;

import aleksandrenaneishvili.carbook.dto.ServiceForCarDto;
import aleksandrenaneishvili.carbook.entity.ServiceForCar;
import aleksandrenaneishvili.carbook.repository.ServiceForCarRepository;
import aleksandrenaneishvili.carbook.service.ServiceForCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceForCarServiceImpl implements ServiceForCarService {

  private final ServiceForCarRepository serviceForCarRepository;

  @Override
  public ServiceForCarDto add(ServiceForCarDto serviceDto) {
    ServiceForCar service = ServiceForCar.builder()
        .carId(serviceDto.getCarId())
        .date(serviceDto.getDate())
        .name(serviceDto.getName())
        .price(serviceDto.getPrice())
        .build();
    ServiceForCar newService = serviceForCarRepository.save(service);
    return ServiceForCarDto.builder()
        .carId(newService.getCarId())
        .date(newService.getDate())
        .name(newService.getName())
        .price(newService.getPrice())
        .build();
  }

  @Override
  public List<ServiceForCarDto> getAllServicesForCar(Long carId) {
    return serviceForCarRepository.findAllByCarId(carId).stream()
        .map(serviceForCar -> ServiceForCarDto.builder()
            .carId(serviceForCar.getCarId())
            .date(serviceForCar.getDate())
            .name(serviceForCar.getName())
            .price(serviceForCar.getPrice())
            .build()).collect(Collectors.toList());
  }
}
