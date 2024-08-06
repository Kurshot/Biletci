package Biletci.dto;

import Biletci.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleDTO extends BaseDTO{
    private String vehicleName;
    private VehicleType vehicleType;
    private int capacity;

}
