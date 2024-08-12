package Biletci.dto;

import Biletci.enums.VehicleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleDTO extends BaseDTO{

    @NotBlank(message = "Vehicle name cannot be blank")
    private String vehicleName;

    @NotNull(message = "Vehicle type cannot be null")
    private VehicleType vehicleType;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 1, message = "Capacity must be greater than 0")
    private int capacity;

}
