package com.me.example.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
    private String message;
    private T data;
}
