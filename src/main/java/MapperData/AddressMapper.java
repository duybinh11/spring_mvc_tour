package MapperData;

import Dto.Request.AddressRequest;
import Dto.Response.AddressResponse;
import Entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    Address toAddress(AddressRequest addressRequest);
    AddressResponse toAddressResponse(Address address);
}
