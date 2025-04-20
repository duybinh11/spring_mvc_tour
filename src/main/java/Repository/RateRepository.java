package Repository;

import Entity.Rate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {
    @EntityGraph(attributePaths = {"customer", "customer.user"})
    List<Rate> findAllByTour_Id(Long id);

}
