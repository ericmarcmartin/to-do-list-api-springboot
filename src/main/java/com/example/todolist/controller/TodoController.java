package com.example.todolist.controller;

import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins ={"http://localhost:3001/", "http://localhost:8090/"})
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    @CrossOrigin
    @GetMapping
    public List<Todo> getTodos() {
        return todoService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Todo addTodos(@RequestBody Todo todo){
        return todoService.AddTodos(todo);
    }

    @PutMapping(path = "/{id}")
    public Todo updateTodo(@PathVariable Integer id, @RequestBody Todo todoToBeUpdated){
        return todoService.update(id, todoToBeUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Integer id){
        todoService.delete(id);
    }

}
