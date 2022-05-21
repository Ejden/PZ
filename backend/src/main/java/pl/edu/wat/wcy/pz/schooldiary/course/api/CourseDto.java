package pl.edu.wat.wcy.pz.schooldiary.course.api;

import pl.edu.wat.wcy.pz.schooldiary.student.domain.Student;

import java.util.List;
import java.util.UUID;

public record CourseDto(
        UUID id,
        String name,
        String schoolYear,
        List<Student> students
) {
}
