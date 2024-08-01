package Biletci.repository;

import Biletci.model.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionRepository extends JpaRepository<Occasion, Long> {
}
