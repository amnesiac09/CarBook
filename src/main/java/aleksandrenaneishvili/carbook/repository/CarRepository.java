package aleksandrenaneishvili.carbook.repository;

import aleksandrenaneishvili.carbook.entity.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
  List<Car> findAllByOwnerUsername(String ownerUsername, Pageable pageable);
  Optional<Car> findByIdAndOwnerUsername(Long id, String ownerUsername);
  boolean existsByIdAndOwnerUsername(Long id, String ownerUsername);

  @Query(value = """
            SELECT * FROM contacts c
            WHERE (:id IS NULL OR c.id = :id)
              AND (c.owner_username = :ownerUsername)
              AND (:make IS NULL OR c.make LIKE %:make%)
              AND (:model IS NULL OR c.model LIKE %:model%)
              AND (:vinCode IS NULL OR c.vin_code LIKE %:vinCode%)
              AND (:licensePlate IS NULL OR c.license_plate LIKE %:licensePlate%)
              AND (:color IS NULL OR c.color LIKE %:color%)
            """, nativeQuery = true)
  List<Car> searchCars(
      @Param("id") Long id,
      @Param("ownerUsername") String ownerUsername,
      @Param("make") String make,
      @Param("model") String model,
      @Param("vinCode") String vinCode,
      @Param("licensePlate") String licensePlate,
      @Param("color") String color,
      Pageable pageable
  );
}
