package com.techschool.service;

import com.techschool.carriers.ServiceResponse;
import com.techschool.entity.Author;
import com.techschool.entity.Course;
import com.techschool.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    AuthorRepository authorRepository;

    public Author fetchByLoginName(String loginName) {
        Author author = null;
        Optional<Author> optAuthor = authorRepository.findByLoginName(loginName);
        if (optAuthor.isPresent()) {
            author = optAuthor.get();
        }
        return author;
    }

    public void fetchCoursesByAuthor(String authorName, ServiceResponse response) {
        try {
            Optional<Author> optAuthor = authorRepository.findByLoginName(authorName);
            List<Course> allCourses = new ArrayList<>();
            optAuthor.ifPresent((a) -> allCourses.addAll(a.getCourses()));
            if (allCourses == null || allCourses.isEmpty()) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("No Courses Found");
                return;
            }
            response.setResponse(allCourses);
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("CourseService.fetchAllCourses", e);
        }
    }
    }
