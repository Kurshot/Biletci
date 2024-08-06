package Biletci.service;

import Biletci.dto.SeatDTO;
import Biletci.dto.VehicleDTO;
import Biletci.mapper.VehicleMapper;
import Biletci.model.Seat;
import Biletci.model.Vehicle;
import Biletci.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public List<VehicleDTO> getAllVehicles(){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOs = vehicles.stream()
                .map(vehicleMapper::toDTO)
                .collect(Collectors.toList());
        return vehicleDTOs;
    }

    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            return vehicleMapper.toDTO(vehicleOptional.get());
        } else {
            return null;
        }
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toDTO(vehicle);
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepository.existsById(vehicleDTO.getId())) {
            Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
            vehicle = vehicleRepository.save(vehicle);
            return vehicleMapper.toDTO(vehicle);
        } else {
            return null;
        }
    }

    public boolean deleteVehicle(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setActive(false);
            vehicleRepository.save(vehicle);
            return true;
        } else {
            return false;
        }
    }
}
