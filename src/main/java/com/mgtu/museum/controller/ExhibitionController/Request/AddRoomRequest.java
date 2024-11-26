package com.mgtu.museum.controller.ExhibitionController.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddRoomRequest {
    @NonNull
    private Integer roomId;
    @NonNull
    private Integer exhibitionId;
}
