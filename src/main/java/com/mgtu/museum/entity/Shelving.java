package com.mgtu.museum.entity;


import com.mgtu.museum.Enum.ShelvingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
public class Shelving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    private Integer exhibition_id;

    @Size(max = 16)
    @NotNull
    @Column(name = "type", nullable = false, length = 16)
    private ShelvingType type;

    @NotNull
    private Integer roomId;

}
