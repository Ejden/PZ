package pl.edu.wat.wcy.pz.schooldiary.student.domain;

import javax.validation.constraints.NotBlank;

public record StudentCreateRequest(
        @NotBlank String courseId,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String username,
        @NotBlank String password
) {
}
