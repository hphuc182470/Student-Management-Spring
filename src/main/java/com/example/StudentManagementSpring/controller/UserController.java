package com.example.StudentManagementSpring.controller;

import com.example.StudentManagementSpring.dto.LoginDTO;
import com.example.StudentManagementSpring.dto.RegisterDTO;
import com.example.StudentManagementSpring.entities.User;
import com.example.StudentManagementSpring.services.JwtService;
import com.example.StudentManagementSpring.services.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserInfoService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/teacher/teacherProfile")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public String teacherProfile() {
        return "Welcome to Teacher Profile";
    }

    @GetMapping("/student/studentProfile")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String studentProfile() {
        return "Welcome to Student Profile";
    }

    /**
     * Func to Register account
     *
     * @param registerDTO {@link RegisterDTO}
     * @return ResponseEntity
     * */
    @PostMapping("/registerAccount")
    public ResponseEntity<RegisterDTO> registerAccount(@RequestBody RegisterDTO registerDTO) {
        try {
            User registeredUser = service.registerUser(registerDTO);
            return new ResponseEntity<>(registerDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(registerDTO, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Func to Generate token
     *
     * @param authRequest {@link LoginDTO}
     * @return ResponseEntity
     * */
    @PostMapping("/generateToken") // login
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody LoginDTO authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user request!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

//    @GetMapping("/user/userProfileAndBorrowing/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public ResponseEntity<UserInfo> getUserByID(@PathVariable Long id) {
//        try {
//            UserInfo userInfo = service.findUserById(id);
//            final var borrow = bookBorrowingClient.findByUserID(id);
//            userInfo.setBorrowingList(borrow.getBody());
//            return ResponseEntity.ok(userInfo);
//        } catch (Exception e) {
//            log.error("Error retrieving user and borrowing info for ID: " + id, e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @GetMapping("/user/testResponse/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public ResponseEntity<UserResponse> findUserByIDByRes(@PathVariable Long id) {
//        try {
//            UserResponse userResponse = service.findUserByIDByRes(id);
//            final var borrow = bookBorrowingClient.findByUserID(id);
//            userResponse.setBorrowingList(borrow.getBody());
//            return ResponseEntity.ok(userResponse);
//        } catch (Exception e) {
//            log.error("Error retrieving user and borrowing info for ID: " + id, e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }

}
