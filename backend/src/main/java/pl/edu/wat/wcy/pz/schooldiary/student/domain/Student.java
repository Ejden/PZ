package pl.edu.wat.wcy.pz.schooldiary.student.domain;

import java.util.UUID;

public record Student(
        UUID id,
        String firstName,
        String lastName,
        String username,
        String password
) {
}

