package com.mgtu.museum.controller.ExhibitController.response;

import com.mgtu.museum.Enum.PackagingType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetExhibitResponse {
    private Integer id;
    private PackagingType packagingType;
    private String name;
}
