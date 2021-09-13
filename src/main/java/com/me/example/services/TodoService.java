package com.me.example.services;

import com.me.example.dtos.TodoDto;
import com.me.example.errors.exceptions.DataNotFoundException;
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
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Transactional
    public void createTodo(TodoDto dto) {
        Todo todo = Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();

        todoRepository.save(todo);
    }

    @Transactional
    public void updateTodo(Long id, TodoDto data) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new DataNotFoundException("Todo not found by id " + id);
        }

        Todo updateTodo = todoOptional.get();
        updateTodo.setTitle(data.getTitle());
        updateTodo.setDescription(data.getDescription());
        updateTodo.setStatus(data.getStatus());

        todoRepository.save(updateTodo);
    }

    @Transactional
    public void deleteTodoById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new DataNotFoundException("Todo not found by id " + id);
        }

        todoRepository.deleteById(id);
    }
}
