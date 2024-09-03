package com.example.StudentManagementSpring.controller;

import com.example.StudentManagementSpring.dto.RegisterDTO;
import com.example.StudentManagementSpring.entities.Exercise;
import com.example.StudentManagementSpring.entities.User;
import com.example.StudentManagementSpring.repositories.UserRepository;
import com.example.StudentManagementSpring.response.ExerciseResponse;
import com.example.StudentManagementSpring.services.ExerciseService;
import com.example.StudentManagementSpring.services.impl.ExerciseServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
@Slf4j
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final UserRepository userRepository;

    /**
     * Func to get all exercises
     *
     * @param id {@link Long}
     * @return ResponseEntity
     * */
    @GetMapping("/getAllExercises")
    public List<ExerciseResponse> getAllExercises(Long id) {
        try {
            List<ExerciseResponse> list = exerciseService.findAll(id);
            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    /**
     * Func to get exercises by id
     *
     * @param id {@link Long}
     * @return ResponseEntity
     * */
    @GetMapping("/getAExercisesByID/{id}")
    public ResponseEntity<ExerciseResponse> getAExercisesByID(@PathVariable Long id) {
        try {
            ExerciseResponse exercise = exerciseService.findByIDByRes(id);
            if (exercise == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(exercise);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    /**
     * Func to get exercises by title
     *
     * @param title {@link String}
     * @return ResponseEntity
     * */
    @GetMapping("/getAExercisesByTitle")
    public ResponseEntity<ExerciseResponse> getAExercisesByTitle(@RequestParam String title) {
        try {
            ExerciseResponse exercise = exerciseService.findByTitle(title);
            if (exercise == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(exercise);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    // only student
//    @GetMapping("/student/getAExercisesByID")
//    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
//    public ResponseEntity<Exercise> enterExercise(@RequestParam String enterCode) {
//        try {
//            Exercise exercise = exerciseService.enterExercise(enterCode);
//            if (exercise == null) {
//                return ResponseEntity.notFound().build();
//            }
//            return ResponseEntity.ok(exercise);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return null;
//    }

    //    @PostMapping("/teacher/addAndModifyExercise")
//    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
//    public ResponseEntity<ExerciseResponse> addAndModifyExercise(@RequestBody Exercise exercise) {
//        try {
//            ExerciseResponse addAndMoExercise = exerciseService.addOrModifyExercise(id, exercise);
//            return ResponseEntity.ok(addAndMoExercise);
//        } catch (RuntimeException e) {
//            log.error("Error processing exercise: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    /**
     * Func to get all exercises
     * only allow ROLE_TEACHER
     *
     * @param exercise {@link Exercise}
     * @return ResponseEntity
     * */
    @PostMapping("/teacher/addAndModifyExercise")
    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    public ResponseEntity<ExerciseResponse> addOrModifyExercise(@RequestBody Exercise exercise) {
        // Get the logged-in user's information
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                username = (String) principal;
            }
        }

        // Retrieve the User entity from the repository using the username
        User loggedInUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Pass the User and Exercise to the service
        ExerciseResponse response = exerciseService.addOrModifyExercise(loggedInUser, exercise);

        return ResponseEntity.ok(response);
    }
}
