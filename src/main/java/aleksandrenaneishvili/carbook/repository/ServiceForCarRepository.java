package aleksandrenaneishvili.carbook.repository;

import aleksandrenaneishvili.carbook.entity.ServiceForCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceForCarRepository extends JpaRepository<ServiceForCar, Long> {
  List<ServiceForCar> findAllByCarId(Long id);
}
