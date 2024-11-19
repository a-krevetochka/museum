package com.mgtu.museum.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class ExhibitionExhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    private Integer exhibitId;

    @NotNull
    private Integer shelfId;

    @NotNull
    private Integer exhibitionId;

    @NotNull
    private Integer descriptionId;

}
