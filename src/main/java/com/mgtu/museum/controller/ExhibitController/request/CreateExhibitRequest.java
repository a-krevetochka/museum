package com.mgtu.museum.controller.ExhibitController.request;

import com.mgtu.museum.Enum.PackagingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class CreateExhibitRequest {
    @NonNull
    private String exhibitName;

    private PackagingType packagingType;
    @NonNull
    private Integer storageShelfId;
}
