package Service;

import Dto.Request.RateRequest;
import Dto.Response.RateResponse;
import Entity.Customer;
import Entity.Rate;
import Entity.Tour;
import Exception1.ResourceNotFoundException;
import MapperData.RateMapper;
import Repository.CustomerRepository;
import Repository.RateRepository;
import Repository.TourRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TourRespository tourRespository;
    @Autowired
    private RateMapper rateMapper;

    public RateResponse addRate(RateRequest rateRequest) {
        Rate rate = rateMapper.toRate(rateRequest);
        Customer customer = customerRepository.findByUserId(rateRequest.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Tour tour = tourRespository.findById(rateRequest.getIdTour())
                .orElseThrow(() -> new ResourceNotFoundException("tour not found"));

        rate.setCustomer(customer);
        rate.setTour(tour);
        return rateMapper.toRateResponse(rateRepository.save(rate));
    }

    public List<RateResponse> getAllRateByIdTour(Long idTour) {
        List<Rate> rates = rateRepository.findAllByTour_Id(idTour);
        return rates.stream().map(rate -> rateMapper.toRateResponse(rate)).collect(Collectors.toList());
    }
}
