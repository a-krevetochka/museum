package com.mgtu.museum.controller.StorageUnitController.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShelvingRequest {
    @NonNull
    private Integer shelvingId;
    @NonNull
    private String description;
}
