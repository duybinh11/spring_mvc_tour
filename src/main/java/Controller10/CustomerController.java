package Controller10;


import Dto.Request.CustomerRequest;
import Dto.Response.CustomerResponse;
import Entity.Customer;
import MapperData.CustomerMapper;
import Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public CustomerResponse add(@RequestBody CustomerRequest customerRequest) {
        return customerService.add(customerRequest);
    }
}
