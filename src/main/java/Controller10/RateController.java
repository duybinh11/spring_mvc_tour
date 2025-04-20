package Controller10;


import Dto.Request.RateRequest;
import Dto.Response.RateResponse;
import Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class RateController {
    @Autowired
    private RateService rateService;
    @PostMapping
    public RateResponse addRate(@RequestBody RateRequest rateRequest) {
        return  rateService.addRate(rateRequest);
    }

    @GetMapping("/{idTour}")
    public List<RateResponse> getAllRate(@PathVariable Long idTour) {
        return  rateService.getAllRateByIdTour(idTour);
    }
}
