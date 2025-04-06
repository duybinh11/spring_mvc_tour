package MapperData;

import DTO.Request.UserRequest;
import DTO.Response.CartResponse;
import DTO.Response.UserResponse;
import DTO.Response.UserResponseWithCart;
import Entity.CartEntity;
import Entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserEntity toUserEntity(UserRequest userRequest);
    List<CartResponse> toListCartResponse(List<CartEntity> cartEntities);
    CartResponse toCartResponse(CartEntity cartEntity);
    UserResponse toUserResponse(UserEntity userEntity);
    UserResponseWithCart toUserResponseWithCart(UserEntity userEntity);
    public void updateUser(UserRequest userRequest,@MappingTarget UserEntity userEntity);
}
