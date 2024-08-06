package Biletci.service;

import Biletci.dto.CompanyDTO;
import Biletci.dto.VoyageDTO;
import Biletci.enums.ResultMapping;
import Biletci.mapper.CompanyMapper;
import Biletci.mapper.VoyageMapper;
import Biletci.model.Company;
import Biletci.model.User;
import Biletci.model.Voyage;
import Biletci.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private VoyageMapper voyageMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;


    public List<VoyageDTO> getAllVoyages() {
        List<Voyage> voyages = voyageRepository.findAll();
        List<VoyageDTO> voyageDTOs = voyages.stream()
                .map(voyageMapper::toDTO)
                .collect(Collectors.toList());
        return voyageDTOs;
    }

    public VoyageDTO getVoyageById(Long id) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (voyageOptional.isPresent()) {
            VoyageDTO voyageDTO = voyageMapper.toDTO(voyageOptional.get());
            return voyageDTO;
        } else {
            return null;
        }
    }

    public VoyageDTO createVoyage(VoyageDTO voyageDTO) {
        CompanyDTO companyDTO = companyService.getCompanyById(voyageDTO.getCompany().getId());
        Company company = companyMapper.toEntity(companyDTO);

        if (company != null) {
            Voyage voyage = voyageMapper.toEntity(voyageDTO);
            voyage.setCompany(company);

            Voyage savedVoyage = voyageRepository.save(voyage);
            VoyageDTO savedVoyageDTO = voyageMapper.toDTO(savedVoyage);

            return savedVoyageDTO;
        } else {
            return null;
        }
    }


    public VoyageDTO updateVoyage(VoyageDTO voyageDTO) {
        if (voyageRepository.existsById(voyageDTO.getId())) {
            Voyage voyage = voyageMapper.toEntity(voyageDTO);
            voyage = voyageRepository.save(voyage);
            VoyageDTO updatedVoyageDTO = voyageMapper.toDTO(voyage);
            return updatedVoyageDTO;
        } else {
            return null;
        }
    }

    public boolean deleteVoyage(Long id) {

        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (voyageOptional.isPresent())
        {
            Voyage voyage = voyageOptional.get();
            voyage.setActive(false);
            voyageRepository.save(voyage)   ;
            return true;
        } else {
            return false;
        }
    }

}
