package com.me.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.example.dtos.TodoDto;
import com.me.example.models.Todo;
import com.me.example.models.TodoStatus;
import com.me.example.repositories.TodoRepository;
import com.me.example.services.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTests {
    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TodoService todoService;

    @MockBean
    TodoRepository todoRepository;

    /*
    @BeforeEach and @BeforeAll are the JUnit 5 equivalents of @Before and @BeforeClass

    Run only once before all tests (initiate along with class need "static")
    @BeforeAll
    public static void setup() {
    }

    @BeforeEach
    public void setup() {
    }
    */

    @Test
    void getAllTodos_200Success() throws Exception {
        Todo todo1 = Todo.builder()
                .id(1L)
                .title("Task A")
                .description("Description A")
                .status(TodoStatus.DONE)
                .build();

        Todo todo2 = Todo.builder()
                .id(2L)
                .title("Task B")
                .description("Description B")
                .status(TodoStatus.IN_PROGRESS)
                .build();

        List<Todo> todos = List.of(todo1, todo2);

        when(todoService.getTodos()).thenReturn(todos);

        this.mvc.perform(
                        get("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    void getTodoById_200Success() throws Exception {
        Todo todo = Todo.builder()
                .id(1L)
                .title("Task A")
                .description("Description A")
                .status(TodoStatus.DONE)
                .build();

        when(todoService.getTodoById(anyLong())).thenReturn(todo);

        this.mvc.perform(
                        get("/todo/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.title").isString())
                .andExpect(jsonPath("$.data.description").isString())
                .andExpect(jsonPath("$.data.status").value("DONE"));
    }

    @Test
    void createNewTodo_200Success() throws Exception {
        TodoDto newTodo = TodoDto.builder()
                .title("Task C")
                .description("Description C")
                .status(TodoStatus.IN_PROGRESS)
                .build();

        this.mvc.perform(
                        post("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(newTodo))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateTodo_200Success() throws Exception {
        TodoDto todo = TodoDto.builder()
                .title("Task C")
                .description("Description C")
                .status(TodoStatus.IN_PROGRESS)
                .build();

        doNothing().when(todoService).updateTodo(anyLong(), any(TodoDto.class));

        this.mvc.perform(
                        put("/todo/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(todo))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteTodo_200Success() throws Exception {
        doNothing().when(todoService).deleteTodoById(anyLong());

        this.mvc.perform(
                        delete("/todo/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenRequestBodyIsInvalid_thenReturnStatus400() throws Exception {
        this.mvc.perform(
                        post("/todo")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(new TodoDto()))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.code")
                                .value("INVALID_REQUEST")
                );
    }
}
