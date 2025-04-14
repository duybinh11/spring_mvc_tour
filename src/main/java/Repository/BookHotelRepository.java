package Repository;

import Entity.BookHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookHotelRepository extends JpaRepository<BookHotel, Long> {
    List<BookHotel> findAllByCustomer_Id(Long idCustomer);
    List<BookHotel> findAllByHotel_Id(Long idHotel);
}
