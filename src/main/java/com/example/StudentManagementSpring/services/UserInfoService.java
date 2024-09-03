package com.example.StudentManagementSpring.services;

import com.example.StudentManagementSpring.dto.RegisterDTO;
import com.example.StudentManagementSpring.entities.Role;
import com.example.StudentManagementSpring.entities.User;
import com.example.StudentManagementSpring.globalExceptions.NotFoundException;
import com.example.StudentManagementSpring.mapper.UserMapper;
import com.example.StudentManagementSpring.repositories.RoleRepository;
import com.example.StudentManagementSpring.repositories.UserRepository;
import com.example.StudentManagementSpring.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

//    public UserInfoService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
//        super();
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUsername(username);
        return userDetail.map(UserInfoDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    /**
     * Func to Add new user
     *
     * @param user {@link User}
     * @return String
     */
    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Added Successfully";
    }

    /**
     * Func to find a user
     *
     * @param id {@link Long}
     * @return User
     */
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found by id: " + id));
    }

    /**
     * Func to find a user and then return to UserResponse using mapstruct
     *
     * @param id {@link Long}
     * @return UserResponse
     */
    public UserResponse findUserByIDByRes(Long id) {
        Optional<User> userInfo = userRepository.findById(id);
        return userInfo.map(UserMapper.INSTANCE::userToUserResponse)
                .orElseThrow(() -> new NotFoundException("User not found by id: " + id));
    }

    /**
     * Func to Register new account
     *
     * @param registerDTO {@link RegisterDTO}
     * @return registerUser
     */
    public User registerUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role role = roleRepository.findById(registerDTO.getRoleID())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoleID(role);

        userRepository.save(user);
        return user;
    }

    // find class of teacherID

    // create Exercise (teacher)

    // add - modify score of student (teacher)


//    public UserResponse findUserByIDByRes(Long id) {
//        Optional<UserInfo> userInfo = repository.findById(id);
//        return userInfo.map(UserMapper.INSTANCE::userToUserResponse)
//                .orElseThrow(() -> new NotFoundException("User not found by id: " + id));
//    }
}
