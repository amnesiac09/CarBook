package aleksandrenaneishvili.carbook.impl;

import aleksandrenaneishvili.carbook.dto.CarDto;
import aleksandrenaneishvili.carbook.entity.Car;
import aleksandrenaneishvili.carbook.repository.CarRepository;
import aleksandrenaneishvili.carbook.service.CarService;
import aleksandrenaneishvili.carbook.service.UserService;
import com.sun.jdi.InternalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;

  private final UserService userService;

  @Override
  public CarDto add(CarDto carDto) {
    Car car = Car.builder()
        .ownerUsername(userService.getCurrentUser().getUsername())
        .color(carDto.getColor())
        .model(carDto.getModel())
        .make(carDto.getMake())
        .licensePlate(carDto.getLicensePlate())
        .vinCode(carDto.getVinCode())
        .build();
    Car newCar = carRepository.save(car);
    return CarDto.builder()
        .ownerUsername(newCar.getOwnerUsername())
        .color(carDto.getColor())
        .model(carDto.getModel())
        .make(carDto.getMake())
        .licensePlate(carDto.getLicensePlate())
        .vinCode(carDto.getVinCode())
        .build();
  }

  @Override
  public CarDto update(Long carId, CarDto carDto) {
    Optional<Car> optionalCar = carRepository.findByIdAndOwnerUsername(carId, userService.getCurrentUser().getUsername());
    if (optionalCar.isEmpty()) {
      throw new InternalException("Car with provided id doesn't exist");
    }

    Car car = optionalCar.get();
    car.setColor(carDto.getColor());
    car.setMake(carDto.getMake());
    car.setModel(carDto.getModel());
    car.setVinCode(carDto.getVinCode());
    car.setLicensePlate(carDto.getLicensePlate());
    Car newCar = carRepository.save(car);

    return CarDto.builder()
        .ownerUsername(newCar.getOwnerUsername())
        .color(carDto.getColor())
        .model(carDto.getModel())
        .make(carDto.getMake())
        .licensePlate(carDto.getLicensePlate())
        .vinCode(carDto.getVinCode())
        .build();
  }

  @Override
  public void delete(Long carId) {
    if (!carRepository.existsByIdAndOwnerUsername(carId, userService.getCurrentUser().getUsername())) {
      throw new InternalException("Car with provided id doesn't exist");
    }
    carRepository.deleteById(carId);
  }

  @Override
  public List<CarDto> getAllCarsForUser(Pageable pageable) {
    return carRepository.findAllByOwnerUsername(userService.getCurrentUser().getUsername(), pageable)
        .stream().map(car -> CarDto.builder()
            .ownerUsername(car.getOwnerUsername())
            .model(car.getModel())
            .make(car.getMake())
            .color(car.getColor())
            .vinCode(car.getVinCode())
            .licensePlate(car.getLicensePlate())
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<CarDto> search(CarDto carDto, Pageable pageable) {
    return carRepository.searchCars(carDto.getId(), carDto.getOwnerUsername(), carDto.getMake(),
        carDto.getModel(), carDto.getVinCode(), carDto.getLicensePlate(), carDto.getColor(), pageable)
        .stream().map(car -> CarDto.builder()
            .ownerUsername(car.getOwnerUsername())
            .model(car.getModel())
            .make(car.getMake())
            .color(car.getColor())
            .vinCode(car.getVinCode())
            .licensePlate(car.getLicensePlate())
            .build())
        .collect(Collectors.toList());
  }
}
