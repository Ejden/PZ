package pl.edu.wat.wcy.pz.schooldiary.student.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.Student;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.StudentCreateRequest;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.StudentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/student")
class StudentController {
    private final StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "")
    public ResponseEntity<StudentsDto> getStudents() {
        return ResponseEntity.ok(
                new StudentsDto(studentService.getStudents().stream()
                        .map(StudentDto::fromStudent).toList())
        );
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(
            @PathVariable String studentId
    ) {
        return ResponseEntity.ok(
                StudentDto.fromStudent(
                        studentService.getStudentById(UUID.fromString(studentId))
                )
        );
    }

    @PostMapping(path = "")
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody StudentCreateRequest createRequest
    ) {
        Student student = studentService.createStudent(createRequest);
        return ResponseEntity
                .created(URI.create(String.format("/api/student/%s", student.id().toString())))
                .body(student);
    }
}
