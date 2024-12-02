package com.mgtu.museum.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer number;

    @NotNull
    private Integer shelvingId;

    private String description;

}
