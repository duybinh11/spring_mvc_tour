package MapperData;

import Dto.Request.TourRequest;
import Dto.Request.TourUpdateRequest;
import Dto.Response.TourResponse;
import Entity.ImageTour;
import Entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AddressMapper.class,ActivityScheduleMapper.class})
public interface TourMapper {
    Tour toTour(TourRequest tourRequest);
    void tourUpdatetoTour(TourUpdateRequest tourUpdateRequest, @MappingTarget Tour tour);
    TourResponse toTourResponse(Tour tour);

    default List<ImageTour> map(List<String> urls) {
        if (urls == null) {
            return null;
        }
        return urls.stream().map(url -> {
            ImageTour img = new ImageTour();
            img.setImg(url);
            return img;
        }).toList();
    }


    default List<String> mapBack(List<ImageTour> imgs) {
        if (imgs == null) {
            return null;
        }
        return imgs.stream()
                .map(ImageTour::getImg)
                .toList();
    }


//    @AfterMapping
//    default void linkTour(@MappingTarget Tour tour) {
//        if (tour.getImgs() != null) {
//            tour.getImgs().forEach(img -> img.setTour(tour));
//        }
//    }
}
