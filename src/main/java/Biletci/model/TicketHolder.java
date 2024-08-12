package Biletci.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TicketHolder extends BaseEntity{
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String mailAddress;

    @OneToMany(mappedBy = "ticketHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();
}
