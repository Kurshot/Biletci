package Biletci.repository;

import Biletci.dto.VoyageDTO;
import Biletci.enums.City;
import Biletci.enums.VehicleType;
import Biletci.model.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByVehicle_VehicleTypeAndDepartureCityAndArrivalCityAndDate(VehicleType vehicleType, City departureCity, City arrivalCity, LocalDate date);
}
