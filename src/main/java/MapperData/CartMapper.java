package MapperData;

import DTO.Request.CartUpdateRequest;
import DTO.Response.CartResponse;
import Entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {
    CartResponse toCartResponse(CartEntity cartEntity);
    public void updateCart(CartUpdateRequest cartUpdateRequest, @MappingTarget CartEntity cartEntity);
}
