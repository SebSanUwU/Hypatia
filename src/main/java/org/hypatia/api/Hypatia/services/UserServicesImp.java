package org.hypatia.api.Hypatia.services;

import org.hypatia.api.Hypatia.dto.UserDto;
import org.hypatia.api.Hypatia.exceptions.UserBadRequestException;
import org.hypatia.api.Hypatia.exceptions.UserNameNotFoundException;
import org.hypatia.api.Hypatia.model.User;
import org.hypatia.api.Hypatia.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServicesImp implements UserServices {

    private final UserRepository userRepository;

    public UserServicesImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) throws UserNameNotFoundException {
        if (userRepository.findByEmail(email) == null){
            throw new UserNameNotFoundException();
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public void createUser(User user) throws UserBadRequestException {
        if(user.getName() == null
                || user.getLastName() == null
                || user.getEmail() == null
                || user.getPassword() == null) throw new UserBadRequestException();
        else {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user, UserDto userDto) {
        // No es necesario Eliminar el usuario (LA LLAVE NUNCA SE ACTUALIZA )
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }
}
