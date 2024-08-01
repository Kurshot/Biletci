package Biletci.controller;

import Biletci.dto.OccasionDTO;
import Biletci.enums.ResultMapping;
import Biletci.service.GenericServiceResult;
import Biletci.service.OccasionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/occasions")
public class OccasionController {

    @Autowired
    private OccasionService occasionService;

    @GetMapping
    public GenericServiceResult getAllOccasions(){
        List<OccasionDTO> result = occasionService.getAllOccasions();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getOccasionById(@PathVariable Long id) {
        OccasionDTO result = occasionService.getOccasionById(id);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
        else{
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createOccasion(@RequestBody OccasionDTO occasionDTO) {
        OccasionDTO result = occasionService.createOccasion(occasionDTO);
        return new GenericServiceResult(ResultMapping.CREATED, result);
    }

    @PutMapping
    public GenericServiceResult updateOccasion(@RequestBody OccasionDTO occasionDTO){
        OccasionDTO result = occasionService.updateOccasion(occasionDTO);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteOccasion(@PathVariable Long id) {
        boolean result = occasionService.deleteOccasion(id);
        if (result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }

}
