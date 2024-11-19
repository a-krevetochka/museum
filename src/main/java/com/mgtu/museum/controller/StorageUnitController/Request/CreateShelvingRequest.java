package com.mgtu.museum.controller.StorageUnitController.Request;

import com.mgtu.museum.Enum.ShelvingType;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CreateShelvingRequest {
    @NonNull
    private Integer shelvingNumber;

    private Integer exhibitionId;
    private Integer roomId;
    @NonNull
    private ShelvingType type;
}
