package com.mgtu.museum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints .NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Exhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 256)
    @NotNull
    private String name;

    @NotNull
    private String receiptNumber;
}
