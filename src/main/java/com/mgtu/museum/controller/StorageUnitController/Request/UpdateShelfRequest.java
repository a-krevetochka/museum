package com.mgtu.museum.controller.StorageUnitController.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShelfRequest {
    @NonNull
    private int shelfId;
    @NonNull
    private String description;
}
