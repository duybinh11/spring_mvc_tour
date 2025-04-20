package Dto.Request;

import lombok.Getter;

import java.util.Date;

@Getter
public class BookTourUpdateRequest {
    private Long id;
    private Integer countMember;
    private Date dateStart;
    private Integer totalMoney;
}
