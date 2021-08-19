package com.example.todolist.integration;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegarationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown(){
        todoRepository.deleteAll();
    }

    @Test
    public void should_return_todos_when_call_get_all_api() throws Exception {
        //given
        todoRepository.saveAll(todolistDataFactory());

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(todolistDataFactory().size()))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].text").value("Eat"))
                .andExpect(jsonPath("$[0].done").value("true"));
    }

    public List<Todo> todolistDataFactory(){
        List<Todo> todoList = new ArrayList<>();
        todoList.add(new Todo(1, "Eat", true));
        todoList.add(new Todo(2, "Code", true));
        todoList.add(new Todo(3, "Exercise", true));
        todoList.add(new Todo(4, "Sleep", false));

        return todoList;
    }
}
