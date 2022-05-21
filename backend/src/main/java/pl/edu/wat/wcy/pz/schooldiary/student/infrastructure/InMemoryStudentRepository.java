package pl.edu.wat.wcy.pz.schooldiary.student.infrastructure;

import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.Student;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.StudentNotFoundException;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
class InMemoryStudentRepository implements StudentRepository {
    private final Map<UUID, Student> students = new HashMap<>();

    @Override
    public Student findById(UUID studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        return students.values().stream().toList();
    }

    @Override
    public void save(Student student) {
        students.put(student.id(), student);
    }
}
