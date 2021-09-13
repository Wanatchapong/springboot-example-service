package com.me.example.controllers;

import com.me.example.models.Todo;
import com.me.example.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @PostMapping
    public ResponseEntity<String> createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
        return ResponseEntity.ok("Created successful");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodo(@PathVariable("id") Long id, @RequestBody Todo todo) {
        todoService.updateTodo(id, todo);
        return ResponseEntity.ok("Updated successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.ok("Deleted successful");
    }
}
