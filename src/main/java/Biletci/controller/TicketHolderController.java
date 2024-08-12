package Biletci.controller;

import Biletci.dto.TicketHolderDTO;
import Biletci.enums.ResultMapping;
import Biletci.service.GenericServiceResult;
import Biletci.service.TicketHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketHolder")
public class TicketHolderController {

    @Autowired
    private TicketHolderService ticketHolderService;

    @GetMapping
    public GenericServiceResult getAllTicketHolders() {
        List<TicketHolderDTO> result = ticketHolderService.getAllTicketHolders();
        return new GenericServiceResult(ResultMapping.SUCCESS, result);
    }

    @GetMapping("/{id}")
    public GenericServiceResult getTicketHolderById(@PathVariable Long id) {
        TicketHolderDTO result = ticketHolderService.getTicketHolderById(id);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @PostMapping
    public GenericServiceResult createTicketHolder(@RequestBody TicketHolderDTO ticketHolderDTO) {
        TicketHolderDTO result = ticketHolderService.createTicketHolder(ticketHolderDTO);
        return new GenericServiceResult(ResultMapping.CREATED, result);
    }

    @PutMapping
    public GenericServiceResult updateTicketHolder(@RequestBody TicketHolderDTO ticketHolderDTO) {
        TicketHolderDTO result = ticketHolderService.updateTicketHolder(ticketHolderDTO);
        if(result == null) {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        } else {
            return new GenericServiceResult(ResultMapping.SUCCESS, result);
        }
    }

    @DeleteMapping("/{id}")
    public GenericServiceResult deleteTicketHolder(@PathVariable Long id) {
        boolean result = ticketHolderService.deleteTicketHolder(id);
        if(result) {
            return new GenericServiceResult(ResultMapping.SUCCESS, null);
        } else {
            return new GenericServiceResult(ResultMapping.NOT_FOUND, null);
        }
    }
}