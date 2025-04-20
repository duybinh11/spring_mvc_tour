package Dto.Request;

import lombok.Getter;

@Getter
public class CustomerUpdateRequest {
    private Long idUser;
    private String username;
    private String phone;
    private String img;
}
