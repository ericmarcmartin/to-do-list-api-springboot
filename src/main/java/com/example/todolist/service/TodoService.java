package com.example.todolist.service;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    public List<Todo> getAll() {
       return todoRepository.findAll();
    }

    public Todo AddTodos(Todo todo) {
        return todoRepository.save(todo);
    }
}
