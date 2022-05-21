package pl.edu.wat.wcy.pz.schooldiary.course.infrastructure;

import org.springframework.stereotype.Repository;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.Course;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.CourseNotFoundException;
import pl.edu.wat.wcy.pz.schooldiary.course.domain.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
class InMemoryCourseRepository implements CourseRepository {
    private final Map<UUID, Course> courses = new HashMap<>();

    @Override
    public Course findById(UUID courseId) {
        return courses.values().stream()
                .filter(course -> course.id().equals(courseId))
                .findFirst()
                .orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<Course> findAll() {
        return courses.values().stream().toList();
    }

    @Override
    public void save(Course course) {
        courses.put(course.id(), course);
    }

    @Override
    public void addStudent(UUID courseId, UUID studentId) {
        Course course = courses.get(courseId);
        if (course == null) {
            throw new CourseNotFoundException();
        }
        course.studentIds().add(studentId);
    }
}