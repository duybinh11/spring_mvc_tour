package Dto.Request;

import lombok.Getter;

import java.util.Date;

@Getter
public class BookTourRequest {
    private Long idUser;
    private Long idTour;
    private int countMember;
    private Date dateStart;
    private int totalMoney;
    private String paymentMethod;
}
