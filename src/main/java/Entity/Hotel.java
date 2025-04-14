package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel extends AbstractEntity<Long> {
    private String name;
    @OneToOne()
    private AddressHotel addressHotel;
    @OneToOne
    private UserEntity user;
    private String img;
    private int price;

    @OneToMany(mappedBy = "hotel")
    private List<BookHotel> bookHotels;
}
