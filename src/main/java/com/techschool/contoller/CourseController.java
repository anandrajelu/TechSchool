package com.techschool.contoller;

import com.techschool.carriers.ServiceResponse;
import com.techschool.entity.Course;
import com.techschool.service.AuthorService;
import com.techschool.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses")
public class CourseController extends BaseController {

    @Autowired
    CourseService courseService;

    @Autowired
    AuthorService authorService;

    @GetMapping(params = "author")
    public ResponseEntity<?> fetchCourseByAuthor(@RequestParam("author") String authorName) {
        ServiceResponse response = new ServiceResponse();
        authorService.fetchCoursesByAuthor(authorName, response);
        return getResponseEntity(response);
    }

    @GetMapping
    public ResponseEntity<?> fetchAllCourses() {
        ServiceResponse response = new ServiceResponse();
        courseService.fetchAllCourses(response);
        return getResponseEntity(response);
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        ServiceResponse response = new ServiceResponse();
        courseService.createNewCourse(course, response);
        return getResponseEntity(response);
    }

    @PutMapping(value = "/{id}", params = {"author", "publish"})
    public ResponseEntity<?> editCourse(@PathVariable long id, @RequestParam("author") String author,
                                        @RequestParam("publish") boolean publish) {
        ServiceResponse response = new ServiceResponse();
        if (publish) {
            courseService.publishCourse(id, response);
        } else {
            courseService.createDraftCourse(id, author, response);
        }
        return getResponseEntity(response);
    }
}
