package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "rate")
public class Rate  extends AbstractEntity<Long>{
    private float rateStar;
    private String comment;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Tour tour;


}
