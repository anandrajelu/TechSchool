package com.techschool.contoller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("GET /courses")
    void test_fetchAllCourses() throws Exception {
        mockMvc.perform(get("/courses")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /courses/100000?author=JosephCale01&publish=false")
    void test_editCourse_draft() throws Exception {
        mockMvc.perform(put("/courses/100000")
                .queryParam("author", "JosephCale01")
                .queryParam("publish", "false")
        ).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("/courses/1?author=JosephCale01&publish=true")
    void test_editCourse_publish() throws Exception {
        mockMvc.perform(put("/courses/1")
                .queryParam("author", "JosephCale01")
                .queryParam("publish", "true")
        ).andExpect(status().isOk());
    }

}