package Biletci.service;

import Biletci.dto.*;
import Biletci.enums.TicketStatus;
import Biletci.mapper.*;
import Biletci.model.*;
import Biletci.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private TicketWithTicketHolderMapper ticketWithTicketHolderMapper;


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

    @Transactional
    public TicketWithTicketHolderDTO createTicket(PurchaseTicketDTO purchaseTicketDTO) {

        if (purchaseTicketDTO.getTicketType().equals(VOYAGE)) {
            VoyageDTO voyageDTO = Optional.ofNullable(voyageService.getVoyageById(purchaseTicketDTO.getVoId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Voyage does not exist"));

            if (voyageDTO.getEmptySeatCount() == 0) // Yer yoksa
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No empty seats available on this voyage");

            // Ticket Holder olusturma işlemleri.
            // Henüz DB'ye kayıt etmiyoruz çünkü ticket holder'a daha ticket atanacak.
            TicketHolderDTO ticketHolderDTO = purchaseTicketDTO.getTicketHolder();
            TicketHolder ticketHolder = ticketHolderMapper.toEntity(ticketHolderDTO); // Ticket Holder'da henüz ticket bilgisi yok.
            if (ticketHolderDTO.getTickets() == null) // Null ise boş liste ata
                ticketHolderDTO.setTickets(new ArrayList<>());

            TicketDTO ticketDTO = ticketMapper.purchaseTicketDTOtoTicketDTO(purchaseTicketDTO);

            // ticketDTO ' yu elimizdeki veriler ile dolduruyoruz.
            ticketDTO.setName(voyageDTO.getName() + " bileti"); // Voyage ismi + bileti: örneğin ( İstanbul > Ankara bileti )
            ticketDTO.setSeatNumber(purchaseTicketDTO.getSeatNumber()); // FE'den seçilen koltuk numarası.
            ticketDTO.setDepartureCity(voyageDTO.getDepartureCity()); // voyage'dan
            ticketDTO.setArrivalCity(voyageDTO.getArrivalCity()); // voyage'dan
            ticketDTO.setCompanyName(voyageDTO.getCompany().getCompanyName()); // voyage -> company ' dan
            ticketDTO.setTicketStatus(TicketStatus.PURCHASED);
            ticketDTO.setTicketType(VOYAGE);
            ticketDTO.setVoId(voyageDTO.getId());

            ticketHolderDTO.getTickets().add(ticketDTO);
            ticketHolderMapper.toEntity(ticketHolderDTO);
            ticketHolderRepository.save(ticketHolder); // Artık ticket holder'da ilgili ticket bilgisi var.


            // TicketDTO -> Ticket mapping
            Ticket ticket  = ticketMapper.toEntity(ticketDTO); // Ticket'da henüz ticket holder bilgisi yok.
            ticket.setTicketHolder(ticketHolder); // Artık ticket'da ilgili ticket holder bilgisi var.
            ticketRepository.save(ticket); // Ticket save


            // Voyage'da boş koltuk sayısını 1 azalt.
            int seatNumber = ticketDTO.getSeatNumber();
            voyageDTO.setEmptySeatCount(voyageDTO.getEmptySeatCount() - 1); // Koltuk sayısını güncelle


            // İlgili koltuğu güncelle
            SeatDTO seatDTO = voyageService.findSeatByNumber(voyageDTO, seatNumber);
            Seat seat = seatMapper.toEntity(seatDTO);
            seat.setTicketHolder(ticketHolder);

            // Veritabanı nesnelerini güncelle
            voyageRepository.save(voyageMapper.toEntity(voyageDTO));
            seatRepository.save(seat);


            TicketWithTicketHolderDTO ticketWithTicketHolderDTO = new TicketWithTicketHolderDTO();
            ticketWithTicketHolderDTO = ticketWithTicketHolderMapper.toTicketWithTicketHolderDTO(ticketDTO);
            ticketWithTicketHolderDTO.setTicketHolder(ticketHolderDTO);


            return ticketWithTicketHolderDTO;
        } else if (purchaseTicketDTO.getTicketType().equals(OCCASION)) {
            OccasionDTO occasionDTO = Optional.ofNullable(occasionService.getOccasionById(purchaseTicketDTO.getVoId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Occasion does not exist"));
            // TODO: Occasion düzenlenecek
            return null;
        } else {
            return null;
        }
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