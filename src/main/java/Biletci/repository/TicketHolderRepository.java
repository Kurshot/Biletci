package Biletci.repository;

import Biletci.model.TicketHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketHolderRepository extends JpaRepository<TicketHolder, Long> {
}
