package Entity;


import jakarta.persistence.*;
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
@Table(name = "customer")
public class Customer extends AbstractEntity<Long> {
    private String username;
    private String phone;
    private String img;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity user;

    @OneToMany(mappedBy = "customer")
    private List<Rate> rates;

    @OneToMany(mappedBy = "customer")
    private List<BookTour> bookTours;


}
