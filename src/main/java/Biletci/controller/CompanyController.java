package Biletci.controller;

import Biletci.dto.CompanyDTO;
import Biletci.dto.UserDTO;
import Biletci.enums.ResultMapping;
import Biletci.service.CompanyService;
import Biletci.service.GenericServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public GenericServiceResult getAllCompanise(){
        List<CompanyDTO> result = companyService.getAllCompanies();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getCompanyById(@PathVariable Long id) {
        CompanyDTO result = companyService.getCompanyById(id);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO result = companyService.createCompany(companyDTO);
        return new GenericServiceResult(ResultMapping.CREATED,companyDTO);
    }

    @PutMapping("/{id}")
    public GenericServiceResult updateCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO result = companyService.updateCompany(companyDTO);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteCompany(@PathVariable Long id) {
        boolean result = companyService.deleteCompany(id);
        if (result == true) {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }
}