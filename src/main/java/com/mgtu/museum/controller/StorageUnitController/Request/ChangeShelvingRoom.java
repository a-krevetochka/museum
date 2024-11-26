package com.mgtu.museum.controller.StorageUnitController.Request;


import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ChangeShelvingRoom {
    @NonNull
    private Integer shelvingId;
    @NonNull
    private Integer roomId;
}
