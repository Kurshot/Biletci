package Biletci.controller;

import Biletci.dto.UserDTO;
import Biletci.dto.VoyageDTO;
import Biletci.enums.ResultMapping;
import Biletci.model.Voyage;
import Biletci.service.GenericServiceResult;
import Biletci.service.VoyageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/voyages")
public class VoyageController {

    private VoyageService voyageService;

    @GetMapping
    public GenericServiceResult getAllVoyages(){
        List<VoyageDTO> result = voyageService.getAllVoyages();
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
        return new GenericServiceResult(ResultMapping.CREATED, voyageDTO);
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
