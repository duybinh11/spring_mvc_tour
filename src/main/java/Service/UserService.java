package Service;


import DTO.Request.UserRequest;
import DTO.Response.UserResponse;
import DTO.Response.UserResponseWithCart;
import Entity.UserEntity;
import Exception1.ResourceNotFoundException;
import MapperData.UserMapper;
import Repository.Criteria.UserCriteria;
import Repository.Specifications.UserSpecifications;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCriteria userCriteria;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse add (UserRequest userRequest) {
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity =  userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    public List<UserResponse> all() {
        return userRepository.findAll().stream().map(u-> userMapper.toUserResponse(u)).toList();
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public UserResponse fix (UserRequest userRequest,int id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        userMapper.updateUser(userRequest,userEntity.get());
        return userMapper.toUserResponse(userRepository.save(userEntity.get()));
    }

    public boolean login(UserRequest userRequest) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(userRequest.getUsername());
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        return passwordEncoder.matches(userRequest.getPassword(), userEntity.get().getPassword());
    }

    public UserResponse getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found");
        }
        return userMapper.toUserResponse(userEntity.get());
    }

    public UserResponse getUserByUsername(String username) {
        Specification<UserEntity> spec = UserSpecifications.hasUsername(username);
        UserEntity userEntity = userRepository.findOne(spec).get();
        return userMapper.toUserResponse(userEntity);
    }

    public UserResponseWithCart getUserByUsernameAndCount(String username, int count) {
        Specification<UserEntity> spec = UserSpecifications.hasUsernameAndCount(username,count);
        Optional<UserEntity> userEntity = userRepository.findOne(spec);
        if(!userEntity.isPresent()) {
            throw new ResourceNotFoundException("User not found or count !> "+ count);
        }
        return userMapper.toUserResponseWithCart(userEntity.get());
    }
}
