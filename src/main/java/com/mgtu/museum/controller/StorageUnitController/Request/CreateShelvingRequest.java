package com.mgtu.museum.controller.StorageUnitController.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateShelvingRequest {
    @NonNull
    private Integer shelvingNumber;
    @NonNull
    private Integer roomId;
    @NonNull
    private Integer exhibitionId;
}
