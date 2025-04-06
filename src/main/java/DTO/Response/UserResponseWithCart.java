package DTO.Response;

import Entity.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseWithCart {
    private int id;
    private String username;
    private List<CartResponse> carts;
}
