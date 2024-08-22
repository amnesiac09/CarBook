package aleksandrenaneishvili.carbook.service;

import aleksandrenaneishvili.carbook.dto.CarDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
  CarDto add(CarDto carDto);
  CarDto update(Long carId, CarDto carDto);
  void delete(Long carId);
  List<CarDto> getAllCarsForUser(Pageable pageable);
  List<CarDto> search(CarDto carDto, Pageable pageable);
}
