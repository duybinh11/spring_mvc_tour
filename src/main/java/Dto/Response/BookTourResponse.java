package Dto.Response;

import Entity.Customer;
import Entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookTourResponse {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String modifiedBy;
    private String status;
    private int countMember;
    private Date dateStart;
    private int totalMoney;
    private String paymentMethod;
    private TourResponse tour;
}
