package Biletci.service;

import Biletci.dto.*;
import Biletci.enums.ResultMapping;
import Biletci.mapper.*;
import Biletci.model.*;
import Biletci.repository.*;
import org.hibernate.annotations.ColumnTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static Biletci.enums.TicketType.OCCASION;
import static Biletci.enums.TicketType.VOYAGE;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private VoyageMapper voyageMapper;

    @Autowired
    private OccasionService occasionService;

    @Autowired
    private OccasionMapper occasionMapper;

    @Autowired
    private TicketHolderService ticketHolderService;

    @Autowired
    private TicketHolderMapper ticketHolderMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private TicketHolderRepository ticketHolderRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private VoyageRepository voyageRepository;

    public TicketDTO getTicketById(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            TicketDTO ticketDTO = ticketMapper.toDTO(ticketOptional.get());
            return ticketDTO;
        } else {
            return null;
        }
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketDTOs = ticketMapper.toDTOList(tickets);
        return ticketDTOs;
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        // Veritabanı nesneleri için gerekli servis çağrıları yapılacak
        if (ticketDTO.getTicketType().equals(VOYAGE)) {
            VoyageDTO voyageDTO = Optional.ofNullable(voyageService.getVoyageById(ticketDTO.getVoId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voyage does not exist"));

            if (voyageDTO.getEmptySeatCount() == 0) // Yer yoksa
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No empty seats available on this voyage");

            // Ticket nesnesini oluştur ve ilişkilendir
            Ticket ticket = ticketMapper.toEntity(ticketDTO);
            ticket.setVoId(voyageDTO.getId()); // Voyage ilişkisini ekle
            ticketRepository.save(ticket);

            int seatNumber = ticketDTO.getSeatNumber();
            voyageDTO.setEmptySeatCount(voyageDTO.getEmptySeatCount() - 1); // Koltuk sayısını güncelle

            // TicketHolder ilişkisini yönet
            TicketHolderDTO ticketHolderDTO = ticketHolderMapper.toDTO(ticket.getTicketHolder());
            if (ticketHolderDTO.getTickets() == null) // Null ise boş liste ata
                ticketHolderDTO.setTickets(new ArrayList<>());

            ticketHolderDTO.getTickets().add(ticketDTO); // Ticket holder'a ticket ekle

            // İlgili koltuğu güncelle
            SeatDTO seatDTO = voyageService.findSeatByNumber(voyageDTO, seatNumber);
            seatDTO.setTicketHolder(ticketDTO.getTicketHolder());

            // Veritabanı nesnelerini güncelle
            ticketHolderRepository.save(ticketHolderMapper.toEntity(ticketHolderDTO));
            voyageRepository.save(voyageMapper.toEntity(voyageDTO));
            seatRepository.save(seatMapper.toEntity(seatDTO));

            return ticketDTO;
        } else if (ticketDTO.getTicketType().equals(OCCASION)) {
            OccasionDTO occasionDTO = Optional.ofNullable(occasionService.getOccasionById(ticketDTO.getVoId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Occasion does not exist"));
            // TODO: Occasion düzenlenecek
            return null;
        } else {
            return null;
        }
    }


//    @Transactional
//    public TicketDTO createTicket(TicketDTO ticketDTO) {
//        Ticket ticket = new Ticket();
//
//
//        if(ticketDTO.getTicketType().equals(VOYAGE)){
//            VoyageDTO voyageDTO = Optional.ofNullable(voyageService.getVoyageById(ticketDTO.getVoId()))
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voyage does not exist"));
//
//            if(voyageService.getVoyageById( ticketDTO.getVoId() ).getEmptySeatCount() == 0) // yer yok ise
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No empty seats available on this voyage");
//            // Yer var ise;
//
//            ticket = ticketMapper.toEntity(ticketDTO);
//            ticketRepository.save(ticket);
//
//            // TODO : Çeşitli validasyonlar yapılacak
//
//            int seatNumber = ticketDTO.getSeatNumber();
//
//
//            voyageDTO.setEmptySeatCount(voyageDTO.getEmptySeatCount() - 1); // Koltuk sayısı 1 düştü.
//
//            // TODO : TicketHolder FE'den gelen verilerle olusturulacak. Şimdilik db'de var olanlar üzerinden test ediyorum.
//
//            TicketHolderDTO ticketHolderDTO = ticketHolderMapper.toDTO(ticket.getTicketHolder());
//            if(ticketHolderDTO.getTickets() == null) // Null ise boş liste ata.
//                ticketHolderDTO.setTickets(new ArrayList<>());
//
//            ticketHolderDTO.getTickets().add(ticketDTO); // Ticket holder' a ticket atandı.
//
//
//            SeatDTO seatDTO = voyageService.findSeatByNumber(voyageDTO, seatNumber); // Voyage'daki ilgili seat'e TicketHolder atanacak.
//            seatDTO.setTicketHolder(ticketDTO.getTicketHolder());
//
//            ticketHolderRepository.save(ticketHolderMapper.toEntity(ticketHolderDTO));
//            voyageRepository.save(voyageMapper.toEntity(voyageDTO));
//            seatRepository.save(seatMapper.toEntity(seatDTO));
//            return ticketDTO;
//        }
//
//        // TODO : Occ düzenlenecek.
//
//        else if(ticketDTO.getTicketType().equals(OCCASION)){
//            OccasionDTO occasionDTO = Optional.ofNullable(occasionService.getOccasionById(ticketDTO.getVoId()))
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Occasion does not exist"));
//            return null;
//
//        }
//        else
//            return null;
//    }

    public TicketDTO updateTicket(TicketDTO ticketDTO) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketDTO.getId());
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketMapper.toEntity(ticketDTO);
            ticket = ticketRepository.save(ticket);
            TicketDTO updatedTicketDTO = ticketMapper.toDTO(ticket);
            return updatedTicketDTO;
        } else {
            return null;
        }
    }

    public boolean deleteTicket(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent())
        {
            Ticket ticket = ticketOptional.get();
            ticket.setActive(false);
            ticketRepository.save(ticket);
            return true;
        } else {
            return false;
        }
    }
}