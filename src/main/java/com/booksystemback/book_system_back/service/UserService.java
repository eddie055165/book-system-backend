package com.booksystemback.book_system_back.service;

import com.booksystemback.book_system_back.model.User;
import com.booksystemback.book_system_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void updateLastLoginTime(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setLastLoginTime(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}