package com.mgtu.museum.controller.ExhibitionController.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class UpdateExhibitionDto {
    @NonNull
    Integer id;
    String name;
    String description;
    Date startDate;
    Date endDate;
}
