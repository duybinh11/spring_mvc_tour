package Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Table(name = "activity_schedule")
public class ActivitySchedule extends AbstractEntity<Long>{
    private String title;
    private String description;

    @ManyToOne
    private Tour tour;
}
