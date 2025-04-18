package MapperData;

import Dto.Request.ActivityScheduleRequest;
import Dto.Response.ActivityScheduleResponse;
import Entity.ActivitySchedule;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ActivityScheduleMapper {
    ActivitySchedule toActivitySchedule(ActivityScheduleRequest activityScheduleRequest);
    ActivityScheduleResponse toActivityScheduleResponse(ActivitySchedule activitySchedule);
}
