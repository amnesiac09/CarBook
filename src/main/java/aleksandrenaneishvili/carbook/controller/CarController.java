package aleksandrenaneishvili.carbook.controller;

import aleksandrenaneishvili.carbook.dto.CarDto;
import aleksandrenaneishvili.carbook.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("car")
@RequiredArgsConstructor
public class CarController {

  private final CarService carService;

  @PostMapping("add")
  public ResponseEntity<?> add(@RequestBody CarDto carDto) {
    try {
      CarDto response = carService.add(carDto);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PutMapping("update/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CarDto carDto) {
    try {
      CarDto response = carService.update(id, carDto);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @DeleteMapping("delete/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    try {
      carService.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("get-all-cars")
  public ResponseEntity<?> getAllCarsForUser(@RequestParam(value = "limit", required = false, defaultValue = "5") int size,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
    try {
      List<CarDto> response = carService.getAllCarsForUser(PageRequest.of(page - 1,
          size, Sort.Direction.DESC, "id"));
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("search")
  public ResponseEntity<?> search(@RequestBody CarDto carDto,
                                         @RequestParam(value = "limit", required = false, defaultValue = "5") int size,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
    try {
      List<CarDto> response = carService.search(carDto, PageRequest.of(page - 1,
          size, Sort.Direction.DESC, "id"));
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
