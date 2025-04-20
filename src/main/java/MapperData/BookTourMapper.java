package MapperData;

import Dto.Request.BookTourRequest;
import Dto.Request.BookTourUpdateRequest;
import Dto.Response.BookTourResponse;
import Entity.BookTour;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.awt.print.Book;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {TourMapper.class}
)
public interface BookTourMapper {
    BookTour toBookTour(BookTourRequest bookTourRequest);
    void updateBookTourFromRequest(BookTourUpdateRequest request, @MappingTarget BookTour bookTour);
    BookTourResponse toBookTourResponse(BookTour bookTour);
}
