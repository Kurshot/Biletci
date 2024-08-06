package Biletci.service;

import Biletci.dto.OccasionPlaceDTO;
import Biletci.mapper.OccasionPlaceMapper;
import Biletci.model.OccasionPlace;
import Biletci.repository.OccasionPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OccasionPlaceService {

    @Autowired
    private OccasionPlaceRepository occasionPlaceRepository;

    @Autowired
    private OccasionPlaceMapper occasionPlaceMapper;

    public List<OccasionPlaceDTO> getAllOccasionPlaces() {
        List<OccasionPlace> occasionPlaces = occasionPlaceRepository.findAll();
        return occasionPlaces.stream()
                .map(occasionPlaceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OccasionPlaceDTO getOccasionPlaceById(Long id) {
        Optional<OccasionPlace> occasionPlaceOptional = occasionPlaceRepository.findById(id);
        if (occasionPlaceOptional.isPresent()) {
            return occasionPlaceMapper.toDTO(occasionPlaceOptional.get());
        } else {
            return null;
        }
    }

    public OccasionPlaceDTO createOccasionPlace(OccasionPlaceDTO occasionPlaceDTO) {
        OccasionPlace occasionPlace = occasionPlaceMapper.toEntity(occasionPlaceDTO);
        occasionPlace = occasionPlaceRepository.save(occasionPlace);
        return occasionPlaceMapper.toDTO(occasionPlace);
    }

    public OccasionPlaceDTO updateOccasionPlace(OccasionPlaceDTO occasionPlaceDTO) {
        if (occasionPlaceRepository.existsById(occasionPlaceDTO.getId())) {
            OccasionPlace occasionPlace = occasionPlaceMapper.toEntity(occasionPlaceDTO);
            occasionPlace = occasionPlaceRepository.save(occasionPlace);
            return occasionPlaceMapper.toDTO(occasionPlace);
        } else {
            return null;
        }
    }

    public boolean deleteOccasionPlace(Long id) {
        Optional<OccasionPlace> occasionPlaceOptional = occasionPlaceRepository.findById(id);
        if (occasionPlaceOptional.isPresent()) {
            OccasionPlace occasionPlace = occasionPlaceOptional.get();
            occasionPlace.setActive(false);
            occasionPlaceRepository.save(occasionPlace);
            return true;
        } else {
            return false;
        }
    }
}