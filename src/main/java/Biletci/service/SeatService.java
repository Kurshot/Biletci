package Biletci.service;

import Biletci.dto.CompanyDTO;
import Biletci.dto.SeatDTO;
import Biletci.mapper.SeatMapper;
import Biletci.model.Company;
import Biletci.model.Seat;
import Biletci.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatMapper seatMapper;

    public List<SeatDTO> getAllSeats(){
        List<Seat> seats = seatRepository.findAll();
        List<SeatDTO> seatDTOS = seats.stream()
                .map(seatMapper::toDTO)
                .collect(Collectors.toList());
        return seatDTOS;
    }

    public SeatDTO getSeatById(Long id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (seatOptional.isPresent()) {
            return seatMapper.toDTO(seatOptional.get());
        } else {
            return null;
        }
    }

    public SeatDTO createSeat(SeatDTO seatDTO) {
        Seat seat = seatMapper.toEntity(seatDTO);
        seat = seatRepository.save(seat);
        return seatMapper.toDTO(seat);
    }

    public SeatDTO updateSeat(SeatDTO seatDTO) {
        if (seatRepository.existsById(seatDTO.getId())) {
            Seat seat = seatMapper.toEntity(seatDTO);
            seat = seatRepository.save(seat);
            return seatMapper.toDTO(seat);
        } else {
            return null;
        }
    }

    public boolean deleteSeat(Long id) {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            seat.setActive(false);
            seatRepository.save(seat);
            return true;
        } else {
            return false;
        }
    }



}
