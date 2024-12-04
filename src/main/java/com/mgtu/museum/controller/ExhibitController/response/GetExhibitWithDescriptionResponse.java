package com.mgtu.museum.controller.ExhibitController.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetExhibitWithDescriptionResponse {
    private Integer exhibitId;
    private String exhibitName;
    private String description;
    private Integer descriptionId;
}
