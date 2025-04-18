package Service;

import Entity.UserEntity;
import MapperData.UserMapper;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserHasRoleService userHasRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntity addUserCustomer(UserEntity userEntity) {
        UserEntity userCreated = saveUser(userEntity);
        userHasRoleService.userHasRoleCustomer(userCreated);
        return userCreated;
    }


    private UserEntity saveUser(UserEntity userEntity){
        String hashPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(hashPassword);
        return userRepository.save(userEntity);
    }
}

