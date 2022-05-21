package pl.edu.wat.wcy.pz.schooldiary.course.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.Course;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.CourseCreateRequest;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.CourseService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/course")
class CourseController {
    private final CourseService courseService;

    CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "")
    public ResponseEntity<CoursesDto> getCourses() {
        return ResponseEntity.ok(
                new CoursesDto(courseService.getCourses())
        );
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(
            @PathVariable String courseId
    ) {
        return ResponseEntity.ok(
                courseService.getCourseById(UUID.fromString(courseId))
        );
    }

    @PostMapping(path = "")
    public ResponseEntity<Course> createCourse(
            @Valid @RequestBody CourseCreateRequest createRequest
    ) {
        Course course = courseService.createCourse(createRequest);
        return ResponseEntity
                .created(URI.create(String.format("/api/course/%s", course.id().toString())))
                .body(course);
    }
}
