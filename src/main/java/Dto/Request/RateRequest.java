package Dto.Request;

import lombok.Getter;

@Getter
public class RateRequest {
    private Long idUser;
    private Long idTour;
    private float rateStar;
    private String comment;
}
