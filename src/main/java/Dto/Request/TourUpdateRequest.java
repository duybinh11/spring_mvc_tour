package Dto.Request;

import lombok.Getter;

@Getter
public class TourUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String type;
}
