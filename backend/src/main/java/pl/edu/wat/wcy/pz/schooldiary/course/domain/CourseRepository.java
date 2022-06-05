package pl.edu.wat.wcy.pz.schooldiary.course.domain;

import java.util.List;
import java.util.UUID;

public interface CourseRepository {
    Course findById(UUID courseId);

    List<Course> findAll();

    void save(Course course);

    void addStudent(UUID courseId, UUID studentId);

    void removeCourse(UUID courseId);
}
