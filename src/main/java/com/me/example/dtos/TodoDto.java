package com.me.example.dtos;

import com.me.example.dtos.validation.ValueOfTodoStatus;
import com.me.example.models.TodoStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    @NotBlank(message = "title is required")
    @Size(max = 100, message = "title must not exceed {max} characters")
    private String title;

    @Size(max = 1000, message = "description must not exceed {max} characters")
    private String description;

    @NotNull(message = "status is required")
    @ValueOfTodoStatus(anyOf = {
            TodoStatus.TODO,
            TodoStatus.IN_PROGRESS,
            TodoStatus.DONE
    })
    private TodoStatus status;
}
