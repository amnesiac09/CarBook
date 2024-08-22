package aleksandrenaneishvili.carbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
  private Long id;
  private String ownerUsername;
  private String licensePlate;
  private String make;
  private String model;
  private String color;
  private String vinCode;
}
