package Biletci.controller;


import Biletci.dto.OccasionPlaceDTO;
import Biletci.enums.ResultMapping;
import Biletci.service.GenericServiceResult;
import Biletci.service.OccasionPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/occasionPlace")
public class OccasionPlaceController {

    @Autowired
    private OccasionPlaceService occasionPlaceService;

    @GetMapping
    public GenericServiceResult getAllOccasionPlaces() {
        List<OccasionPlaceDTO> result = occasionPlaceService.getAllOccasionPlaces();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getOccasionPlaceById(@PathVariable Long id) {
        OccasionPlaceDTO result = occasionPlaceService.getOccasionPlaceById(id);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createOccasionPlace(@RequestBody OccasionPlaceDTO occasionPlaceDTO) {
        OccasionPlaceDTO result = occasionPlaceService.createOccasionPlace(occasionPlaceDTO);
        return new GenericServiceResult(ResultMapping.CREATED, result);
    }

    @PutMapping
    public GenericServiceResult updateOccasionPlace(@RequestBody OccasionPlaceDTO occasionPlaceDTO) {
        OccasionPlaceDTO result = occasionPlaceService.updateOccasionPlace(occasionPlaceDTO);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteOccasionPlace(@PathVariable Long id) {
        boolean result = occasionPlaceService.deleteOccasionPlace(id);
        if(result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }
}
