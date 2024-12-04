package com.mgtu.museum.controller.QrController.request;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Необходимо передать айди экспоната и соответствующее ему айди описания например")

public class GenerateExhibitQrRequest {
    List<Integer> exhibitId;
    List<Integer> descriptionId;
}
