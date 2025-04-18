package Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address extends AbstractEntity<Long> {
    String province;
    String district;
    String town;
    String detail;

    @OneToOne
    private Tour tour;
}
