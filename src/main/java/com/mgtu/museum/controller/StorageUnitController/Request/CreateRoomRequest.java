package com.mgtu.museum.controller.StorageUnitController.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoomRequest {
    @NonNull
    private Integer roomNumber;
}
