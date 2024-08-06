package Biletci.service;

import Biletci.dto.CompanyDTO;
import Biletci.dto.OccasionDTO;
import Biletci.dto.OccasionDTO;
import Biletci.mapper.CompanyMapper;
import Biletci.mapper.OccasionMapper;
import Biletci.mapper.OccasionMapper;
import Biletci.model.Company;
import Biletci.model.Occasion;
import Biletci.model.Occasion;
import Biletci.model.User;
import Biletci.repository.OccasionRepository;
import Biletci.repository.OccasionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OccasionService {

    @Autowired
    private OccasionRepository occasionRepository;

    @Autowired
    private OccasionMapper occasionMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;


    public List<OccasionDTO> getAllOccasions() {
        List<Occasion> occasions = occasionRepository.findAll();
        List<OccasionDTO> occasionDTOS = occasions.stream()
                .map(occasionMapper::toDTO)
                .collect(Collectors.toList());
        return occasionDTOS;
    }

    public OccasionDTO getOccasionById(Long id) {
        Optional<Occasion> occasionOptional = occasionRepository.findById(id);
        if (occasionOptional.isPresent()) {
            OccasionDTO occasionDTO = occasionMapper.toDTO(occasionOptional.get());
            return occasionDTO;
        } else {
            return null;
        }
    }

    public OccasionDTO createOccasion(OccasionDTO occasionDTO) {
        CompanyDTO companyDTO = companyService.getCompanyById(occasionDTO.getCompany().getId());
        Company company = companyMapper.toEntity(companyDTO);

        if (company != null) {
            Occasion occasion = occasionMapper.toEntity(occasionDTO);
            occasion.setCompany(company);

            Occasion savedOccasion = occasionRepository.save(occasion);
            OccasionDTO savedOccasionDTO = occasionMapper.toDTO(savedOccasion);

            return savedOccasionDTO;
        } else {
            return null;
        }
    }


    public OccasionDTO updateOccasion(OccasionDTO occasionDTO) {
        if (occasionRepository.existsById(occasionDTO.getId())) {
            Occasion occasion = occasionMapper.toEntity(occasionDTO);
            occasion = occasionRepository.save(occasion);
            OccasionDTO updatedOccasionDTO = occasionMapper.toDTO(occasion);
            return updatedOccasionDTO;
        } else {
            return null;
        }
    }

    public boolean deleteOccasion(Long id) {
        Optional<Occasion> occasionOptional = occasionRepository.findById(id);
        if (occasionOptional.isPresent())
        {
            Occasion occasion = occasionOptional.get();
            occasion.setActive(false);
            occasionRepository.save(occasion);
            return true;
        } else {
            return false;
        }
    }
}
