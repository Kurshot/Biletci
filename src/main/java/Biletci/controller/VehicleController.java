package Biletci.controller;


import Biletci.dto.VehicleDTO;
import Biletci.enums.ResultMapping;
import Biletci.service.GenericServiceResult;
import Biletci.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public GenericServiceResult getAllVehicles() {
        List<VehicleDTO> result = vehicleService.getAllVehicles();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getVehicleById(@PathVariable Long id) {
        VehicleDTO result = vehicleService.getVehicleById(id);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO result = vehicleService.createVehicle(vehicleDTO);
        return new GenericServiceResult(ResultMapping.CREATED, result);
    }

    @PutMapping
    public GenericServiceResult updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO result = vehicleService.updateVehicle(vehicleDTO);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteVehicle(@PathVariable Long id) {
        boolean result = vehicleService.deleteVehicle(id);
        if (result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }
}