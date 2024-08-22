package aleksandrenaneishvili.carbook.controller;

import aleksandrenaneishvili.carbook.dto.ServiceForCarDto;
import aleksandrenaneishvili.carbook.service.ServiceForCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("service")
@RequiredArgsConstructor
public class ServiceForCarController {

  private final ServiceForCarService serviceForCarService;

  @PostMapping("add")
  public ResponseEntity<?> add(@RequestBody ServiceForCarDto serviceForCarDto) {
    try {
      ServiceForCarDto response = serviceForCarService.add(serviceForCarDto);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("get-all-services-for-car/{carId}")
  public ResponseEntity<?> getAllServicesForCar(@PathVariable Long carId) {
    try {
      List<ServiceForCarDto> response = serviceForCarService.getAllServicesForCar(carId);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
