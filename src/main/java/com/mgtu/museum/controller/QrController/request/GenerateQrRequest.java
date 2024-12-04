package com.mgtu.museum.controller.QrController.request;

import com.mgtu.museum.Enum.QrType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenerateQrRequest {
    List<Integer> ids;
    QrType qrType;
}
