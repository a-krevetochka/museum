package com.mgtu.museum.controller.StorageUnitController.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetShelvingsRequest {
    @NonNull
    Integer roomId;

}
