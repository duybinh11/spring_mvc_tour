package Service;

import Dto.Request.TourRequest;
import Dto.Request.TourUpdateRequest;
import Dto.Response.TourResponse;
import Entity.*;
import Exception1.ResourceNotFoundException;
import MapperData.TourMapper;
import Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    private RateRepository rateRepository;
    @Autowired
    private ImageTourRepository imageTourRepository;
    @Autowired
    private ActivityScheduleRepository activityScheduleRepository;

    public List<TourResponse> getAllTours() {
        List<Tour> tours = tourRespository.findAll();
        return tours.stream().map(tour -> {
            TourResponse tourResponse = tourMapper.toTourResponse(tour);

            List<Rate> rates = rateRepository.findAllByTour_Id(tour.getId());
            if (!rates.isEmpty()) {
                double average = rates.stream()
                        .mapToDouble(Rate::getRateStar)
                        .average()
                        .orElse(0.0);
                double roundedAverage = Math.round(average * 10.0) / 10.0;
                tourResponse.setAverageRate(roundedAverage);
            } else {
                tourResponse.setAverageRate(0.0);
            }

            return tourResponse;
        }).collect(Collectors.toList());
    }

    public List<TourResponse> search(String search) {
        List<Tour> tours = tourRespository.findByNameContainingIgnoreCase(search);


        return tours.stream().map(tour -> {
            TourResponse tourResponse = tourMapper.toTourResponse(tour);

            List<Rate> rates = rateRepository.findAllByTour_Id(tour.getId());
            if (!rates.isEmpty()) {
                double average = rates.stream()
                        .mapToDouble(Rate::getRateStar)
                        .average()
                        .orElse(0.0);
                double roundedAverage = Math.round(average * 10.0) / 10.0;
                tourResponse.setAverageRate(roundedAverage);
            } else {
                tourResponse.setAverageRate(0.0);
            }

            return tourResponse;
        }).collect(Collectors.toList());
    }

    public TourResponse updateTour(TourUpdateRequest tourUpdateRequest) {
        Tour tour =  tourRespository.findById(tourUpdateRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
        tourMapper.tourUpdatetoTour(tourUpdateRequest, tour);
        return tourMapper.toTourResponse(tourRespository.save(tour));
    }

    public void deleteTour(Long idTour) {
        Tour tour =  tourRespository.findById(idTour).orElseThrow(()-> new ResourceNotFoundException("Tour not found"));
        tourRespository.delete(tour);
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
