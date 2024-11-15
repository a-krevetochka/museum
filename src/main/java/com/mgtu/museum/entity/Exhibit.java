package com.mgtu.museum.entity;

import com.mgtu.museum.Enum.PackagingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 64)
    @Column(name = "packaging_type", length = 64)
    private PackagingType packagingType;

    @NotNull
    private Integer storageShelfId;

    @Size(max = 256)
    @NotNull
    @Column(name = "name", nullable = false, length = 256)
    private String name;

}
