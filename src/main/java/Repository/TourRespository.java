package Repository;

import Entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRespository extends JpaRepository<Tour, Long> {
}
