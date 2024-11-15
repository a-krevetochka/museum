package com.mgtu.museum.controller.ExhibitionController.Request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class UpdateExhibitionRequest {
    @NonNull
    Integer id;
    String name;
    String description;
    Date startDate;
    Date endDate;
}
