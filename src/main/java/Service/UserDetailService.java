package Service;

import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(s);
        if (!userEntity.isPresent()) {
            throw new ResourceNotFoundException("Username not found");
        }
        return userEntity.get();
    }



    public List<SimpleGrantedAuthority> permissions(String username){
        Optional<List<String>> permissions = userRepository.findUserGetPermisstion(username);
        if (!permissions.isPresent()) {
            throw new ResourceNotFoundException("Invalid username");
        }
        return permissions.get().stream().map(p -> new SimpleGrantedAuthority(p)).toList();
    }

}
