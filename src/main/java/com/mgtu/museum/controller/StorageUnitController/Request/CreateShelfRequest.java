package com.mgtu.museum.controller.StorageUnitController.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateShelfRequest {
    @NonNull
    Integer shelfNumber;
    @NonNull
    Integer shelvingId;
}
