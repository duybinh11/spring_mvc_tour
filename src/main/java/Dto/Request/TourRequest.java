package Dto.Request;

import lombok.Getter;

import java.util.List;

@Getter
public class TourRequest {
    private String name;
    private String description;
    private int price;
    private String type;
    private AddressRequest address;
    private List<ActivityScheduleRequest> activitySchedules;
    private List<String> imgs;
}
