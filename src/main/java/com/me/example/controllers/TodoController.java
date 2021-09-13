package com.me.example.controllers;

import com.me.example.dtos.SuccessResponse;
import com.me.example.dtos.TodoDto;
import com.me.example.models.Todo;
import com.me.example.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/todo")
@Validated
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Todo>>> getAllTodos() {
        List<Todo> todoList = todoService.getTodos();

        SuccessResponse<List<Todo>> response = SuccessResponse.<List<Todo>>builder()
                .message("Get all todo list")
                .data(todoList)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<Todo>> getTodoById(@PathVariable("id")
                                                             @Positive(message = "id must be integer") Long id) {
        Todo todo = todoService.getTodoById(id);

        SuccessResponse<Todo> response = SuccessResponse.<Todo>builder()
                .message("Get todo")
                .data(todo)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<Void>> createTodo(@Valid @RequestBody TodoDto todo) {
        todoService.createTodo(todo);

        SuccessResponse<Void> response = SuccessResponse.<Void>builder()
                .message("Created successful")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> updateTodo(
            @PathVariable("id")
            @Positive(message = "id must be integer") Long id, @Valid @RequestBody TodoDto todo) {

        todoService.updateTodo(id, todo);

        SuccessResponse<Void> response = SuccessResponse.<Void>builder()
                .message("Updated successful")
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteTodo(
            @PathVariable("id")
            @Positive(message = "id must be integer") Long id) {

        todoService.deleteTodoById(id);

        SuccessResponse<Void> response = SuccessResponse.<Void>builder()
                .message("Deleted successful")
                .build();

        return ResponseEntity.ok(response);
    }
}
