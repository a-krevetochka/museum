package com.mgtu.museum.controller.ExhibitController.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateDescriptionRequest {
    @NonNull
    private String description;
    @NonNull
    private Integer exhibitId;
}
