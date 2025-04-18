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
@Table(name = "tour")
public class Tour extends AbstractEntity<Long>{
    private String name;
    private String description;
    private int price;
    private String type;


    @OneToOne(mappedBy = "tour")
    private Address address;

    @OneToMany(mappedBy = "tour",fetch = FetchType.EAGER)
    private List<ImageTour> imgs;

    @OneToMany(mappedBy = "tour",fetch = FetchType.EAGER)
    private List<ActivitySchedule> activitySchedules;

    @OneToMany(mappedBy = "tour")
    private List<Rate> rates;

    @OneToMany(mappedBy = "tour")
    private List<BookTour> bookTours;


}
