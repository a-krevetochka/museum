package com.mgtu.museum.controller.ExhibitionController.Request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
@Builder
public class CreateExhibitionRequest {
    @NonNull
    String name;
    @NonNull
    String description;
    @NonNull
    Date startDate;
    @NonNull
    Date endDate;
}
