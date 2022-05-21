package pl.edu.wat.wcy.pz.schooldiary.student.domain;

import java.util.List;
import java.util.UUID;

public interface StudentRepository {
    Student findById(UUID studentId);

    List<Student> findAll();

    void save(Student student);
}
