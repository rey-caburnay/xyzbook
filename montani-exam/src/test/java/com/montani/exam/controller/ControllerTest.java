package com.montani.exam.controller;


import com.montani.exam.ExamApplication;
import com.montani.exam.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamApplication.class)
@AutoConfigureMockMvc
@SqlGroup({
//        @Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD),
})
public class ControllerTest {

    @Autowired
    BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetBookByISBN13Test() throws Exception {
        String isbn="978-1-891830-85-3";
        this.mockMvc.perform(get("/book/978-1-891830-85-3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("title").isString());
//                .andExpect(jsonPath("$", aMapWithSize(3)));

//        assertThat(this.repository.findAll()).hasSize(6);
    }
}
