package Controller10;

import Dto.Request.BookTourRequest;
import Dto.Request.BookTourUpdateRequest;
import Dto.Request.TourRequest;
import Dto.Response.BookTourResponse;
import Service.BookTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book_tour")
public class BookTourController {
    @Autowired
    private BookTourService bookTourService;
    @PostMapping
    public BookTourResponse bookTour(@RequestBody BookTourRequest bookTourRequest) {
        return bookTourService.bookTour(bookTourRequest);
    }

    @GetMapping("/{idUser}")
    public List<BookTourResponse> getBookedTourByUserId(@PathVariable Long idUser) {
        return bookTourService.getBookedTourByUserId(idUser);
    }

    @GetMapping()
    public List<BookTourResponse> getAllBookedTour() {
        return bookTourService.getAllBookedTour();
    }

    @PutMapping
    public BookTourResponse updateBookTour(@RequestBody BookTourUpdateRequest tourUpdateRequest) {
        return bookTourService.updaTour(tourUpdateRequest);
    }
    @PutMapping("/status/{idBookTour}")
    public BookTourResponse updateStatus(@RequestBody String status,@PathVariable Long idBookTour) {
        return bookTourService.updateStatus(idBookTour,status);
    }
}
