package com.mgtu.museum.controller;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private HttpStatus status;
    private String message;
    private T data;
}
