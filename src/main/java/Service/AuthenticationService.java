package Service;

import DTO.Request.UserRequest;
import DTO.Response.TokenResponse;
import Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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


    public TokenResponse login(UserRequest userRequest) {
        try {

            List<SimpleGrantedAuthority> authoritiesUser =  userDetailService.permissions(userRequest.getUsername());
            System.out.println("Permission : "+ authoritiesUser);
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getPassword(),
                            authoritiesUser
                    )
            );


            String token = jwtUtil.generateToken(userRequest.getUsername(), authoritiesUser);
            String refreshToken = jwtUtil.generateRefreshToken(userRequest.getUsername(), authoritiesUser);

            return TokenResponse.builder().token(token).refreshToken(refreshToken).build();


        } catch (BadCredentialsException ex) {
            throw ex;
        }
    }
}
