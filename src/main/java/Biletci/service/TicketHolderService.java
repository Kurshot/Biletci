package Biletci.service;

import Biletci.dto.TicketHolderDTO;
import Biletci.mapper.TicketHolderMapper;
import Biletci.model.TicketHolder;
import Biletci.repository.TicketHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketHolderService {

    @Autowired
    private TicketHolderRepository ticketHolderRepository;

    @Autowired
    private TicketHolderMapper ticketHolderMapper;

    public List<TicketHolderDTO> getAllTicketHolders() {
        List<TicketHolder> ticketHolders = ticketHolderRepository.findAll();
        return ticketHolders.stream()
                .map(ticketHolderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TicketHolderDTO getTicketHolderById(Long id) {
        Optional<TicketHolder> ticketHolderOptional = ticketHolderRepository.findById(id);
        if (ticketHolderOptional.isPresent()) {
            return ticketHolderMapper.toDTO(ticketHolderOptional.get());
        } else {
            return null;
        }
    }

    public TicketHolderDTO createTicketHolder(TicketHolderDTO ticketHolderDTO) {
        TicketHolder ticketHolder = ticketHolderMapper.toEntity(ticketHolderDTO);
        ticketHolder = ticketHolderRepository.save(ticketHolder);
        return ticketHolderMapper.toDTO(ticketHolder);
    }

    public TicketHolderDTO updateTicketHolder(TicketHolderDTO ticketHolderDTO) {
        if (ticketHolderRepository.existsById(ticketHolderDTO.getId())) {
            TicketHolder ticketHolder = ticketHolderMapper.toEntity(ticketHolderDTO);
            ticketHolder = ticketHolderRepository.save(ticketHolder);
            return ticketHolderMapper.toDTO(ticketHolder);
        } else {
            return null;
        }
    }

    public boolean deleteTicketHolder(Long id) {
        Optional<TicketHolder> ticketHolderOptional = ticketHolderRepository.findById(id);
        if (ticketHolderOptional.isPresent()) {
            TicketHolder ticketHolder = ticketHolderOptional.get();
            ticketHolder.setActive(false);
            ticketHolderRepository.save(ticketHolder);
            return true;
        } else {
            return false;
        }
    }
}
