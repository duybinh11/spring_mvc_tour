package Controller10;

import DTO.Request.UserRequest;
import DTO.Response.UserResponse;
import DTO.Response.UserResponseWithCart;
import Entity.UserEntity;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        return userService.add(userRequest);
    }

    @PreAuthorize("hasAuthority('FULL_ACCESS')")
    @GetMapping("/users")
    public List<UserResponse> getAll() {
        return userService.all();
    }

    @GetMapping("/me")
    public UserResponse me() {
        return userService.getUser();
    }

    @PutMapping("/users/{id}")
    public UserResponse getAll(@RequestBody UserRequest userRequest, @PathVariable int id) {
        return userService.fix(userRequest,id);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody UserRequest userRequest){
        return userService.login(userRequest);
    }

    @PostMapping("/register")
    public UserResponse handleLogin(@RequestBody UserRequest userRequest){
        return userService.add(userRequest);
    }

    @GetMapping("/debug")
    public String debugAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "Authentication is null";
        }
        return "Authentication: " + auth.getName() + ", Authorities: " + auth.getAuthorities();
    }

    @GetMapping("/userByUsername/{username}")
    public UserResponse userByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/userByUsernameAndcount/{username}/{count}")
    public UserResponseWithCart userByUsernameAndcount(@PathVariable String username, @PathVariable int count) {
        return userService.getUserByUsernameAndCount(username,count);
    }

}
