package org.hypatia.api.Hypatia.services;

import org.hypatia.api.Hypatia.dto.UserDto;
import org.hypatia.api.Hypatia.exceptions.UserAlreadyExistException;
import org.hypatia.api.Hypatia.exceptions.UserBadRequestException;
import org.hypatia.api.Hypatia.exceptions.UserNameNotFoundException;
import org.hypatia.api.Hypatia.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {
    List<User> findUsers();

    User findUserByEmail(String email) throws UserAlreadyExistException, UserNameNotFoundException;

    void createUser(User user) throws UserBadRequestException;

    void deleteUser(User user);

    void updateUser(User user, UserDto userDto);
}
