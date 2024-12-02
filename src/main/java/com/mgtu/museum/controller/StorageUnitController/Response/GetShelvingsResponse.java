package com.mgtu.museum.controller.StorageUnitController.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GetShelvingsResponse {
    Integer id;
    Integer number;
    String description;
}
