package com.mgtu.museum.controller.ExhibitionController.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class CreateExhibitionDto {
    @NonNull
    String name;
    @NonNull
    String description;
    @NonNull
    Date startDate;
    @NonNull
    Date endDate;
}
