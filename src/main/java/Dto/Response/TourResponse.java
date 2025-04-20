package Dto.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TourResponse {
    private Long id;
    private String name;
    private String description;
    private int price;
    private String type;
    private AddressResponse address;
    private List<String> imgs;
    private List<ActivityScheduleResponse> activitySchedules;
    private Double averageRate;
}
