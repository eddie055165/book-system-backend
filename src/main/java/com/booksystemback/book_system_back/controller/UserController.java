package com.booksystemback.book_system_back.controller;



import com.booksystemback.book_system_back.model.User;
import com.booksystemback.book_system_back.repository.UserRepository;
import com.booksystemback.book_system_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // 註冊新使用者
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) {
        if (userRepository.existsByPhoneNumber(newUser.getPhoneNumber())) {
            return new ResponseEntity<>("Phone number already registered", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(newUser);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    // 登入
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginUser.getPhoneNumber());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (loginUser.getPassword().equals(user.getPassword())) {
                userService.updateLastLoginTime(user.getUserId());
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserId")
    public ResponseEntity<?> getUserIdByPhoneNumber(@RequestParam String phoneNumber) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get().getUserId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


    // 獲取所有使用者
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}