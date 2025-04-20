package Service;

import Dto.Request.BookTourRequest;
import Dto.Request.BookTourUpdateRequest;
import Dto.Response.BookTourResponse;
import Entity.BookTour;
import Entity.Customer;
import Entity.Tour;
import Enum1.EnumStatusBook;
import Exception1.ResourceNotFoundException;
import MapperData.BookTourMapper;
import Repository.BookTourRepository;
import Repository.CustomerRepository;
import Repository.TourRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTourService {
    @Autowired
    private BookTourMapper bookTourMapper;
    @Autowired
    private BookTourRepository bookTourRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TourRespository tourRespository;

    public BookTourResponse bookTour(BookTourRequest bookTourRequest) {
        BookTour bookTour = bookTourMapper.toBookTour(bookTourRequest);
        bookTour.setStatus(EnumStatusBook.WAIT.name());
        Customer customer = customerRepository.findByUserId(bookTourRequest.getIdUser()).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Tour tour = tourRespository.findById(bookTourRequest.getIdTour()).orElseThrow(() -> new ResourceNotFoundException("Tour not found"));
        bookTour.setCustomer(customer);
        bookTour.setTour(tour);
        return bookTourMapper.toBookTourResponse(bookTourRepository.save(bookTour));
    }

    public BookTourResponse updaTour(BookTourUpdateRequest tourUpdateRequest) {
        BookTour bookTour =  bookTourRepository.findById(tourUpdateRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("BookTour not found"));
        bookTourMapper.updateBookTourFromRequest(tourUpdateRequest, bookTour);
        return bookTourMapper.toBookTourResponse(bookTourRepository.save(bookTour));
    }
    public BookTourResponse updateStatus(Long id,String status) {
        BookTour bookTour =  bookTourRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookTour not found"));
        bookTour.setStatus(status);
        return bookTourMapper.toBookTourResponse(bookTourRepository.save(bookTour));
    }

    public List<BookTourResponse> getBookedTourByUserId(Long idUser) {
        List<BookTour> bookedTours = bookTourRepository.findAllByCustomer_User_Id(idUser);
        return  bookedTours.stream().map(bookTour -> bookTourMapper.toBookTourResponse(bookTour)).toList();
    }
    public List<BookTourResponse> getAllBookedTour() {
        List<BookTour> bookedTours = bookTourRepository.findAll();
        return  bookedTours.stream().map(bookTour -> bookTourMapper.toBookTourResponse(bookTour)).toList();
    }

}
