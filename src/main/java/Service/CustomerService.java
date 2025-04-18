package Service;

import Dto.Request.CustomerRequest;
import Dto.Request.CustomerUpdateRequest;
import Dto.Response.CustomerResponse;
import Entity.Customer;
import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import MapperData.CustomerMapper;
import Repository.CustomerRepository;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserRepository userRepository;

    public CustomerResponse add(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toCustomer(customerRequest);
        UserEntity userCreated = userService.addUserCustomer(customer.getUser());
        customer.setUser(userCreated);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public CustomerResponse updateCustomer(CustomerUpdateRequest request) {
        Customer customer = customerRepository.findByUserId(request.getIdUser())
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        if (request.getUsername() != null) {
            customer.setUsername(request.getUsername());
        }

        if (request.getPhone() != null) {
            customer.setPhone(request.getPhone());
        }

        if (request.getImg() != null) {
            customer.setImg(request.getImg());
        }

        if (request.getEmail() != null) {
            UserEntity user = customer.getUser();
            user.setEmail(request.getEmail());
            userRepository.save(user);
        }

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

}
