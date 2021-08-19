package com.example.todolist.service;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    TodoRepository todoRepository;
    public List<Todo> getAll() {
       return todoRepository.findAll();
    }
}
