package Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RateResponse {
    private Long id;
    private float rateStar;
    private String comment;
    private CustomerResponse customer;
    private Date createdAt;
}
