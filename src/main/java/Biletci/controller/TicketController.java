package Biletci.controller;

import Biletci.dto.TicketDTO;
import Biletci.dto.TicketDTO;
import Biletci.enums.ResultMapping;
import Biletci.service.GenericServiceResult;
import Biletci.service.TicketService;
import Biletci.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public GenericServiceResult getAllTickets(){
        List<TicketDTO> result = ticketService.getAllTickets();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getTicketById(@PathVariable Long id) {
        TicketDTO result = ticketService.getTicketById(id);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
        else{
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO result = ticketService.createTicket(ticketDTO);
        return new GenericServiceResult(ResultMapping.CREATED, result);
    }

    @PutMapping
    public GenericServiceResult updateTicket(@RequestBody TicketDTO ticketDTO) {
        TicketDTO result = ticketService.updateTicket(ticketDTO);
        if (result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, ticketDTO);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteTicket(@PathVariable Long id) {
        boolean result = ticketService.deleteTicket(id);
        if (result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }
}