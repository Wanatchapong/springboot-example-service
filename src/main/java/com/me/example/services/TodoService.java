package com.me.example.services;

import com.me.example.models.Todo;
import com.me.example.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return (List<Todo>) todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void createTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(Long id, Todo data) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            Todo updateTodo = todo.get();
            updateTodo.setTitle(data.getTitle());
            updateTodo.setDescription(data.getDescription());
            updateTodo.setStatus(data.getStatus());
            todoRepository.save(updateTodo);
        }
    }

    @Transactional
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
