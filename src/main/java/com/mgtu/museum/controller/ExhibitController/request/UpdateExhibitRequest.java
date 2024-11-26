package com.mgtu.museum.controller.ExhibitController.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Builder
public class UpdateExhibitRequest {
    @NonNull
    private Integer exhibitId;
    @NonNull
    private String exhibitName;
}
