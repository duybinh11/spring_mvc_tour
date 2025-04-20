package Repository;


import Entity.BookTour;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookTourRepository extends JpaRepository<BookTour, Long> {
//    @EntityGraph(attributePaths = {"tour","tour.imgs","tour.address","tour.activitySchedules"})
    List<BookTour> findAllByCustomer_User_Id(Long idUser);
}
