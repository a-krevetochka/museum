package com.mgtu.museum.controller;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T>{
    private String message;
    private T data;
}
