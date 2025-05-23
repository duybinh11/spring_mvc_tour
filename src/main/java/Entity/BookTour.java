package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_tour")
public class BookTour extends AbstractEntity<Long>{
    private String status;
    private int countMember;
    private Date dateStart;
    private int totalMoney;
    private String paymentMethod;

    @ManyToOne
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Tour tour;
}
