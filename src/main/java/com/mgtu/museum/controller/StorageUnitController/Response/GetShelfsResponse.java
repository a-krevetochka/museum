package com.mgtu.museum.controller.StorageUnitController.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetShelfsResponse {
    Integer id;
    Integer number;
    String description;
}
