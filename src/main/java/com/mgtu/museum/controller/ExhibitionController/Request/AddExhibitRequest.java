package com.mgtu.museum.controller.ExhibitionController.Request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddExhibitRequest {
    @NonNull
    private Integer exhibitId;
    @NonNull
    private Integer descriptionId;

    private Integer shelfId;

    private Integer roomId;

    private Integer exhibitionId;
}
