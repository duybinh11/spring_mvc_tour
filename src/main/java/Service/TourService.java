package Service;

import Dto.Request.TourRequest;
import Dto.Response.TourResponse;
import Entity.ActivitySchedule;
import Entity.Address;
import Entity.ImageTour;
import Entity.Tour;
import MapperData.TourMapper;
import Repository.ActivityScheduleRepository;
import Repository.AddressRepository;
import Repository.ImageTourRepository;
import Repository.TourRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {
    @Autowired
    private TourMapper tourMapper;

    @Autowired
    private TourRespository tourRespository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ImageTourRepository imageTourRepository;
    @Autowired
    private ActivityScheduleRepository activityScheduleRepository;

    public List<TourResponse> getAllTours() {
        List<Tour> tours = tourRespository.findAll();
        return tours.stream().map(tour -> tourMapper.toTourResponse(tour)).collect(Collectors.toList());
    }


    public TourResponse addTour(TourRequest tourRequest) {
        Tour tour = tourMapper.toTour(tourRequest);

        Address address = tour.getAddress();
        List<ImageTour> imageTours = tour.getImgs();
        List<ActivitySchedule> activitySchedules = tour.getActivitySchedules();

        tour.setAddress(null);
        tour.setImgs(null);
        tour.setActivitySchedules(null);

        Tour newTour = tourRespository.save(tour);
        if(address != null) {
            address.setTour(newTour);
            tour.setAddress(addressRepository.save(address));
        }
        if(!imageTours.isEmpty()) {
            imageTours.forEach(imageTour -> {imageTour.setTour(newTour);});
            tour.setImgs(imageTourRepository.saveAll(imageTours));
        }

        if(!activitySchedules.isEmpty()) {
            activitySchedules.forEach(activitySchedule -> {activitySchedule.setTour(newTour);});
            tour.setActivitySchedules( activityScheduleRepository.saveAll(activitySchedules));
        }

        return tourMapper.toTourResponse(tour);

    }
}
