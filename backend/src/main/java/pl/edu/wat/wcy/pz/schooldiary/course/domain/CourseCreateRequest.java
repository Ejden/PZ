package pl.edu.wat.wcy.pz.schooldiary.course.domain;

import javax.validation.constraints.NotBlank;

public record CourseCreateRequest(
        @NotBlank String name,
        @NotBlank String schoolYear
) {
}
