package Biletci.service;

import Biletci.dto.CompanyDTO;
import Biletci.dto.UserDTO;
import Biletci.enums.ResultMapping;
import Biletci.mapper.CompanyMapper;
import Biletci.model.Company;
import Biletci.model.User;
import Biletci.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;


    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOs = companies.stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
        return companyDTOs;
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            CompanyDTO companyDTO = companyMapper.toDTO(companyOptional.get());
            return companyDTO;
        } else {
            return null;
        }
    }

    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDTO(company);
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        if (companyRepository.existsById(companyDTO.getId())) {
            Company company = companyMapper.toEntity(companyDTO);
            company = companyRepository.save(company);
            CompanyDTO updatedCompanyDTO = companyMapper.toDTO(company);
            return updatedCompanyDTO;
        } else {
            return null;
        }
    }

    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
