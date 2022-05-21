package pl.edu.wat.wcy.pz.schooldiary.student.domain;

import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.CourseRepository;

import java.util.List;
import java.util.UUID;

@Service
public final class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(UUID studentId) {
        return studentRepository.findById(studentId);
    }

    public Student createStudent(StudentCreateRequest createRequest) {
        Student student = new Student(
                UUID.randomUUID(),
                createRequest.firstName(),
                createRequest.lastName(),
                createRequest.username(),
                createRequest.password()
        );
        courseRepository.addStudent(UUID.fromString(createRequest.courseId()), student.id());
        studentRepository.save(student);

        return student;
    }
}
