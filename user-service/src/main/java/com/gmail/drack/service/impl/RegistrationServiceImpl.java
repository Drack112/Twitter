package com.gmail.drack.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.gmail.drack.broker.producer.UpdateUserProducer;
import com.gmail.drack.commons.exceptions.ApiRequestException;
import com.gmail.drack.constants.UserErrorMessage;
import com.gmail.drack.constants.UserSuccessMessage;
import com.gmail.drack.dto.request.RegistrationRequest;
import com.gmail.drack.model.User;
import com.gmail.drack.model.UserRole;
import com.gmail.drack.repository.UserRepository;
import com.gmail.drack.service.RegistrationService;
import com.gmail.drack.service.util.UserServiceHelper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// TODO: FIX exception on kafka export

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final UserServiceHelper userServiceHelper;
    private final UpdateUserProducer updateUserProducer;

    @Override
    @Transactional
    public String registration(RegistrationRequest request, BindingResult bindingResult) {
        userServiceHelper.processInputErrors(bindingResult);
        Optional<User> existingUser = userRepository.getUserByEmail(request.getEmail(), User.class);
        if (existingUser.isEmpty()) {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setUsername(request.getUsername());
            user.setFullName(request.getUsername());
            user.setBirthday(request.getBirthday());
            user.setRole(UserRole.USER);
            userRepository.save(user);
            // updateUserProducer.sendUpdateUserEvent(user);
            return UserSuccessMessage.USER_DATA_CHECKED;
        }

        if (!existingUser.get().isActive()) {
            existingUser.get().setUsername(request.getUsername());
            existingUser.get().setFullName(request.getUsername());
            existingUser.get().setBirthday(request.getBirthday());
            userRepository.save(existingUser.get());
            // updateUserProducer.sendUpdateUserEvent(existingUser.get());
            return UserSuccessMessage.USER_DATA_CHECKED;
        }

        throw new ApiRequestException(UserErrorMessage.EMAIL_HAS_ALREADY_BEEN_TAKEN, HttpStatus.FORBIDDEN);

    }
}
