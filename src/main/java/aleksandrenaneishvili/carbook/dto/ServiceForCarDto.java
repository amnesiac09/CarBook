package aleksandrenaneishvili.carbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceForCarDto {
  private Long id;
  private Long carId;
  private String name;
  private LocalDate date;
  private Double price;
}
