package com.example.todolist.integration;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegarationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void tearDown() {
        todoRepository.deleteAll();
    }


    @Test
    public void should_return_todos_when_call_get_all_api() throws Exception {
        //given
        todoRepository.save(new Todo(1, "Eat", true));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].text").value("Eat"))
                .andExpect(jsonPath("$[0].done").value("true"));
    }

    @Test
    public void should_return_todo_when_call_add_todo_api_given_todo() throws Exception {
        //given
        String todoJson = "{\n" +
                "    \"text\": \"Eat\",\n" +
                "    \"done\": true\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/todos")
                .contentType(APPLICATION_JSON)
                .content(todoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Eat"))
                .andExpect(jsonPath("$.done").value("true"));
    }

//    public List<Todo> todolistDataFactory() {
//        List<Todo> todoList = new ArrayList<>();
//        todoList.add(new Todo(1, "Eat", true));
//        todoList.add(new Todo(2, "Code", true));
//        todoList.add(new Todo(3, "Exercise", true));
//        todoList.add(new Todo(4, "Sleep", false));
//
//        return todoList;
//    }
}
