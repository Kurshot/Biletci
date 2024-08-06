package Biletci.model;

import Biletci.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicle extends BaseEntity{

    private String vehicleName;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType; // FERRY-PLANE-TRAIN-BUS

    private int capacity;
}
