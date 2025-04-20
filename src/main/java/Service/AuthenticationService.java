package Service;


import Dto.Request.UserRequest;
import Dto.Response.CustomerResponse;
import Dto.Response.TokenResponse;
import Entity.Customer;
import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import MapperData.CustomerMapper;
import Repository.CustomerRepository;
import Repository.UserRepository;
import Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;


    public TokenResponse login(UserRequest userRequest) {
        try {

            List<SimpleGrantedAuthority> authoritiesUser =  userDetailService.permissions(userRequest.getEmail());
            System.out.println(authoritiesUser);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getEmail(),
                            userRequest.getPassword(),
                            authoritiesUser
                    )
            );


            String token = jwtUtil.generateToken(userRequest.getEmail(), authoritiesUser);
            String refreshToken = jwtUtil.generateRefreshToken(userRequest.getEmail(), authoritiesUser);

            UserEntity user = userRepository.findByEmail(userRequest.getEmail()).get();

            return TokenResponse.builder().token(token).idUser(user.getId()).refreshToken(refreshToken).build();


        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid password");
        }
    }

    public CustomerResponse me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        String email =  authentication.getName();
        Customer customer =  customerRepository.findByUser_Email(email).orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        return  customerMapper.toCustomerResponse(customer);
    }

}