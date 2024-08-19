package Biletci.controller;

import Biletci.dto.UserDTO;
import Biletci.dto.VoyageDTO;
import Biletci.enums.City;
import Biletci.enums.ResultMapping;
import Biletci.enums.VehicleType;
import Biletci.model.Voyage;
import Biletci.service.GenericServiceResult;
import Biletci.service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/voyage")
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public GenericServiceResult getAllVoyages(){
        List<VoyageDTO> result = voyageService.getAllVoyages();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<VoyageDTO>> getVoyagesByCriteria(
//            @RequestParam String departureCity,
//            @RequestParam String arrivalCity,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//
//        List<VoyageDTO> voyages = voyageService.findVoyages(departureCity, arrivalCity, date);
//        return ResponseEntity.ok(voyages);
//    }

    @GetMapping("/search")
    public GenericServiceResult getVoyagesBySearch(
            @RequestParam VehicleType vehicleType,
            @RequestParam City departureCity,
            @RequestParam City arrivalCity,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){

        List<VoyageDTO> result = voyageService.getVoyagesBySearch(vehicleType, departureCity, arrivalCity, date);
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }



    @GetMapping("/{id}")
    public GenericServiceResult getVoyageById(@PathVariable Long id) {
        VoyageDTO result = voyageService.getVoyageById(id);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
        else{
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createVoyage(@RequestBody VoyageDTO voyageDTO) {
        VoyageDTO result = voyageService.createVoyage(voyageDTO);
        return new GenericServiceResult(ResultMapping.CREATED, result);
    }

    @PutMapping
    public GenericServiceResult updateVoyage(@RequestBody VoyageDTO voyageDTO){
        VoyageDTO result = voyageService.updateVoyage(voyageDTO);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteVoyage(@PathVariable Long id) {
        boolean result = voyageService.deleteVoyage(id);
        if (result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }

}
