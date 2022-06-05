package pl.edu.wat.wcy.pz.schooldiary.course.domain;

import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.pz.schooldiary.course.api.CourseDto;
import pl.edu.wat.wcy.pz.schooldiary.student.domain.StudentRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public final class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(
            CourseRepository courseRepository,
            StudentRepository studentRepository
    ) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public List<CourseDto> getCourses() {
        return courseRepository.findAll().stream()
                .map(this::toCourseDto)
                .toList();
    }

    public CourseDto getCourseById(UUID courseId) {
        Course requestedCourse = courseRepository.findById(courseId);
        return toCourseDto(requestedCourse);
    }

    private CourseDto toCourseDto(Course course) {
        return new CourseDto(
                course.id(),
                course.name(),
                course.schoolYear(),
                course.studentIds().stream().map(studentRepository::findById).toList()
        );
    }

    public Course createCourse(CourseCreateRequest createRequest) {
        Course course = new Course(
                UUID.randomUUID(),
                createRequest.name(),
                createRequest.schoolYear(),
                new ArrayList<>(),
                Instant.now()
        );

        courseRepository.save(course);
        return course;
    }
}
