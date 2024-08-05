package Biletci.service;

import Biletci.dto.CompanyDTO;
import Biletci.dto.OccasionDTO;
import Biletci.dto.TicketDTO;
import Biletci.dto.VoyageDTO;
import Biletci.enums.ResultMapping;
import Biletci.mapper.CompanyMapper;
import Biletci.mapper.OccasionMapper;
import Biletci.mapper.TicketMapper;
import Biletci.mapper.VoyageMapper;
import Biletci.model.*;
import Biletci.repository.CompanyRepository;
import Biletci.repository.TicketRepository;
import Biletci.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /* TODO : == yerine equals kullan +
        null gerek yok +
        voyage occasion kontrolünü farklı metotta yap ?
    */

    public TicketDTO createTicket(TicketDTO ticketDTO, Long paramId) {
        Ticket ticket;

        if(ticketDTO.getTicketType().equals(VOYAGE)){
            VoyageDTO voyageDTO = voyageService.getVoyageById(paramId);
            ticketDTO.setTicketVoyage(voyageDTO);
            ticket = ticketMapper.toEntity(ticketDTO);
            ticketRepository.save(ticket);
            return ticketDTO;
        }
        else if(ticketDTO.getTicketType().equals(OCCASION)){
            OccasionDTO occasionDTO =  occasionService.getOccasionById(paramId);
            ticketDTO.setTicketOccasion(occasionDTO);
            ticket = ticketMapper.toEntity(ticketDTO);
            ticketRepository.save(ticket);
            return ticketDTO;
        }
        else
            return null;
    }



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