package pl.edu.wat.wcy.pz.schooldiary.course.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public
class CourseNotFoundException extends RuntimeException {
}
