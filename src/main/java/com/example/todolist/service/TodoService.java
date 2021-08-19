package com.example.todolist.service;

import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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

    public Todo update(Integer id, Todo todoToBeUpdated) {
        return todoRepository.save(updateTodoInfo(getById(id), todoToBeUpdated));
    }

    private Todo updateTodoInfo(Todo todo, Todo todoToBeUpdated) {
        todoToBeUpdated.setId(todo.getId());
        if (isNull(todoToBeUpdated.getText())) {
            todoToBeUpdated.setDone(!todo.isDone());
            todoToBeUpdated.setText(todo.getText());
            return todoToBeUpdated;
        }
        return todoToBeUpdated;
    }

    private Todo getById(Integer id) {
        return todoRepository
                .findById(id)
                .orElseThrow(null);
    }

    public void delete(Integer id) {
        todoRepository.delete(todoRepository.findById(id).orElseThrow(null));
    }
}
