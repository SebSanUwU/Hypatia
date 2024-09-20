package org.hypatia.api.Hypatia.controller.user;

import org.hypatia.api.Hypatia.dto.UserDto;
import org.hypatia.api.Hypatia.exceptions.UserAlreadyExistException;
import org.hypatia.api.Hypatia.exceptions.UserBadRequestException;
import org.hypatia.api.Hypatia.exceptions.UserNameNotFoundException;
import org.hypatia.api.Hypatia.model.User;
import org.hypatia.api.Hypatia.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServices userServices;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserServices userServices, PasswordEncoder passwordEncoder) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<User>> findUsers(){
        List<User> users = userServices.findUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{email}")
    public ResponseEntity<User> findUser(@PathVariable String email) throws UserAlreadyExistException {
        try {
            User user = userServices.findUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch ( UserNameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        try {
            userServices.findUserByEmail(userDto.getEmail());
            throw new UserAlreadyExistException();
        }catch (UserNameNotFoundException e){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = new User(userDto);
            try {
                userServices.createUser(user);
            }catch (UserBadRequestException ex){
                return ResponseEntity.badRequest().build();
            }
            URI createdUserUri = URI.create("/api/v1/users/" + user.getEmail());
            return ResponseEntity.created(createdUserUri).body(user);
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @PutMapping("{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody UserDto userDto)  {
        try {
            User user = userServices.findUserByEmail(email);
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userServices.updateUser(user, userDto);
            return ResponseEntity.ok(user);
        } catch (UserNameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UserAlreadyExistException ex){
            return ResponseEntity.status(409).build();
        }

    }

    @DeleteMapping("{email}")
    public ResponseEntity<User> deleteUser(@PathVariable String email) throws UserAlreadyExistException {
        try {
            User user = userServices.findUserByEmail(email);
            userServices.deleteUser(user);
            return ResponseEntity.noContent().build();
        } catch (UserNameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
