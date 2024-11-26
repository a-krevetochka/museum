package com.mgtu.museum.controller.ExhibitController.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateExhibitRequest {
    @NonNull
    private String exhibitName;
}
