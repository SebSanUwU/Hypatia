package org.hypatia.api.Hypatia.controller.auth;


import org.hypatia.api.Hypatia.dto.LoginDto;
import org.hypatia.api.Hypatia.dto.TokenDto;
import org.hypatia.api.Hypatia.exceptions.InvalidCredentialsException;
import org.hypatia.api.Hypatia.exceptions.UserAlreadyExistException;
import org.hypatia.api.Hypatia.exceptions.UserNameNotFoundException;
import org.hypatia.api.Hypatia.model.User;
import org.hypatia.api.Hypatia.security.JwtUtil;
import org.hypatia.api.Hypatia.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class    AuthenticationController {

    private final UserServices userServices;

    private final JwtUtil jwtUtil;


    public AuthenticationController(UserServices userServices, JwtUtil jwtUtil) {
        this.userServices = userServices;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws InvalidCredentialsException, UserNameNotFoundException, UserAlreadyExistException {
        User user =userServices.findUserByEmail(loginDto.getUsername());
        if (user != null) {
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
                TokenDto tokenDto = jwtUtil.generateToken(user.getEmail(), user.getRoles());
                return ResponseEntity.ok(tokenDto);
            }else {
                throw new InvalidCredentialsException();
            }
        }else {
            throw new InvalidCredentialsException();
        }
    }
}
