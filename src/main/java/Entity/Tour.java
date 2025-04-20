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


    @OneToOne(mappedBy = "tour",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Address address;

    @OneToMany(mappedBy = "tour",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<ImageTour> imgs;

    @OneToMany(mappedBy = "tour",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<ActivitySchedule> activitySchedules;

    @OneToMany(mappedBy = "tour",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Rate> rates;

    @OneToMany(mappedBy = "tour",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<BookTour> bookTours;

}
