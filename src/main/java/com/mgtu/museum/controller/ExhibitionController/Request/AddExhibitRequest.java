package com.mgtu.museum.controller.ExhibitionController.Request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddExhibitRequest {
    @NonNull
    private int exhibitionId;
    @NonNull
    private int exhibitId;
    @NonNull
    private int descriptionId;
    @NonNull
    private int shelfId;
}
