package ua.nure.tkp.trainingday.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.nure.tkp.trainingday.entity.User;
import ua.nure.tkp.trainingday.entity.dto.UserDto;
import ua.nure.tkp.trainingday.repository.UserRepo;

@Service("loginUserService")
public class LoginUserService {
    private final UserRepo userRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserService.class);

    @Autowired
    public LoginUserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void saveNewUser(UserDto user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        User newUser = new User(
                user.getLogin(), passwordEncoder.encode(user.getPassword()), user.getAge(), user.getNickname());
        userRepo.save(newUser);
        LOGGER.info("New user registered {}", newUser.getNickname());
    }

}
