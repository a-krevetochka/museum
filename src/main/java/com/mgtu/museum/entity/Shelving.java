package com.mgtu.museum.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Shelving {
    @Id
    private Integer id;

    @NotNull
    private Integer number;

    @NotNull
    private Integer roomId;

    @NotNull
    private Integer exhibitionId;

}
