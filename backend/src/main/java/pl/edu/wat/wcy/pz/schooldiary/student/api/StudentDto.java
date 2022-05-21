package pl.edu.wat.wcy.pz.schooldiary.student.api;

import pl.edu.wat.wcy.pz.schooldiary.student.domain.Student;

import java.util.UUID;

public record StudentDto(
        UUID id,
        String firstName,
        String lastName,
        String username,
        String password
) {
    public static StudentDto fromStudent(Student student) {
        return new StudentDto(
                student.id(),
                student.firstName(),
                student.lastName(),
                student.username(),
                student.password()
        );
    }
}
