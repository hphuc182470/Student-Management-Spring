package com.example.StudentManagementSpring.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseResponse {
    private int exerciseID;
    private String title;
    private String enterCode;
}
