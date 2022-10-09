package com.techschool.service;

import com.techschool.Constants;
import com.techschool.carriers.ServiceResponse;
import com.techschool.entity.Author;
import com.techschool.entity.Course;
import com.techschool.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class CourseService {

    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    EntityManager entityManager;

    public void fetchAllCourses(ServiceResponse response) {
        try {
            List<Course> allCourses = courseRepository.findAll();
            if (allCourses == null || allCourses.isEmpty()) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("No Courses Found");
                return;
            }
            response.setResponse(allCourses);
        } catch (Exception e) {
            logger.error("CourseService.fetchAllCourses", e);
        }
    }

    public void createNewCourse(Course course, ServiceResponse response) {
        try {
            if (course == null || course.getName() == null) {
                response.setStatus(BAD_REQUEST);
                response.setMessage("Failure");
            } else {
                List<Author> authors = course.getAuthors();
                List<Author> authorFromDb = new ArrayList<>();
                if (authors != null) {
                    for (Author author: authors) {
                        Author existingAuthor = authorService.fetchByLoginName(author.getLoginName());
                        if (existingAuthor == null) {
                            response.setStatus(BAD_REQUEST);
                            response.setMessage("Author Not Found: "+author.getLoginName());
                            throw new Exception("Author Not Found");
                        } else {
                            authorFromDb.add(existingAuthor);
                        }
                    }
                course.setAuthors(authorFromDb);
                }
                Course savedUser = courseRepository.save(course);
                response.setStatus(CREATED);
                response.setMessage("/courses/" + course.getId());
                response.setResponse(savedUser);
            }
        } catch (Exception e) {
            logger.error("CourseService.createNewCourse", e);
        }
    }

    public void createDraftCourse(long id, String author, ServiceResponse response) {
        try {
            Optional<Course> optCourse = courseRepository.findById(id);
            if (optCourse.isPresent()) {
                Course course = optCourse.get();
                Author auth = authorService.fetchByLoginName(author);
                Course draftCourse = new Course(course, auth);
                Course newCourse = courseRepository.save(draftCourse);
                response.setStatus(CREATED);
                response.setMessage("/courses/"+newCourse.getId());
                response.setResponse(newCourse);
            } else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Failure");
            }
        } catch (Exception e) {
            logger.error("CourseService.createDraftCourse", e);
        }
    }

    public void publishCourse(long id, ServiceResponse response) {
        try {
            Optional<Course> optCourse = courseRepository.findById(id);
            if (optCourse.isPresent()) {
                Course draftCourse = optCourse.get();
                Course parent = draftCourse.getParent();
                draftCourse.setAuthors(parent.getAuthors());
                draftCourse.setSections(parent.getSections());
                parent.setState(Constants.STATE.REMOVED);
                draftCourse.setState(Constants.STATE.PUBLISHED);
                response.setResponse(OK);
                response.setMessage("Success");
                response.setResponse(draftCourse);
            } else {
                response.setStatus(NOT_FOUND);
                response.setMessage("Failure");
            }
        } catch (Exception e) {
            logger.error("CourseService.publishCourse", e);
        }
    }
}
