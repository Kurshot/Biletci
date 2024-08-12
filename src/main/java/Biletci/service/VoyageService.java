package Biletci.service;

import Biletci.dto.CompanyDTO;
import Biletci.dto.SeatDTO;
import Biletci.dto.VehicleDTO;
import Biletci.dto.VoyageDTO;
import Biletci.enums.ResultMapping;
import Biletci.mapper.CompanyMapper;
import Biletci.mapper.VehicleMapper;
import Biletci.mapper.VoyageMapper;
import Biletci.model.*;
import Biletci.repository.SeatRepository;
import Biletci.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private SeatRepository seatRepository;

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
        CompanyDTO companyDTO = Optional.ofNullable(companyService.getCompanyById(voyageDTO.getCompany().getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company does not exist"));

        VehicleDTO vehicleDTO = Optional.ofNullable(vehicleService.getVehicleById(voyageDTO.getVehicle().getId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle does not exist"));

        Company company = companyMapper.toEntity(companyDTO);
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);

        Voyage voyage = voyageMapper.toEntity(voyageDTO);
        voyage.setCompany(company);
        voyage.setVehicle(vehicle);


        // Create Seat List

        List<Seat> seats = new ArrayList<>();
        for(int i = 1; i <= voyage.getVehicle().getCapacity(); i++){
            Seat seat = new Seat();
            seat.setSeatNumber(i); // Seat Number
            seat.setVehicle(vehicle); // Seat'lere vehicle atandı.
            seats.add(seat);
            seatRepository.save(seat);
        }

        voyage.setSeats(seats); // Voyage artık Seat listesine sahip
        voyage.setEmptySeatCount(voyage.getVehicle().getCapacity());

        Voyage savedVoyage = voyageRepository.save(voyage);

        return voyageMapper.toDTO(savedVoyage);
    }

    public SeatDTO findSeatByNumber(VoyageDTO voyageDTO, int seatNumber) {
        Optional<SeatDTO> seatDTOOptional = voyageDTO.getSeats().stream()
                .filter(seatDTO -> seatDTO.getSeatNumber() == seatNumber)
                .findFirst();

        // Eğer bulunursa döndür, bulunmazsa bir istisna fırlat
        return seatDTOOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Seat not found for seat number: " + seatNumber));
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
