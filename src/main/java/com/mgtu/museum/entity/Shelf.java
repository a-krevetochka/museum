package com.mgtu.museum.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    private Integer shelving_id;

}
