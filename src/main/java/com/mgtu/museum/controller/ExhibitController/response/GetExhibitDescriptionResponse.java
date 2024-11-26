package com.mgtu.museum.controller.ExhibitController.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetExhibitDescriptionResponse {
    Integer id;
    String description;
}
