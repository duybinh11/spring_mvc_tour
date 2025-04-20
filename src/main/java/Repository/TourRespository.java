package Repository;

import Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRespository extends JpaRepository<Tour, Long> {
    List<Tour> findByNameContainingIgnoreCase(String keyword);
}
