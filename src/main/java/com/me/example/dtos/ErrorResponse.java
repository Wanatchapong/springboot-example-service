package com.me.example.dtos;

import com.me.example.errors.ErrorDetail;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private List<ErrorDetail> errors;
}
