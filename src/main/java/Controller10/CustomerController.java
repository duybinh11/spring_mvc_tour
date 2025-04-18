package Controller10;


import Dto.Request.CustomerRequest;
import Dto.Request.CustomerUpdateRequest;
import Dto.Response.CustomerResponse;
import Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public CustomerResponse add(@RequestBody CustomerRequest customerRequest) {
        return customerService.add(customerRequest);
    }

    @PutMapping
    public CustomerResponse update(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return customerService.updateCustomer(customerUpdateRequest);
    }
}
