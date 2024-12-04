package com.mgtu.museum.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitWithDescriptionResponse;
import com.mgtu.museum.controller.QrController.dto.NameDescDto;
import com.mgtu.museum.controller.QrController.request.GenerateExhibitQrRequest;
import com.mgtu.museum.controller.QrController.request.GenerateQrRequest;
import com.mgtu.museum.repository.ExhibitRepository;
import com.mgtu.museum.repository.ExhibitionRepository;
import com.mgtu.museum.repository.ShelfRepository;
import com.mgtu.museum.repository.ShelvingRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QRCodeService {
    private final ExhibitRepository exhibitRepository;
    private final ShelfRepository shelfRepository;
    private final ShelvingRepository shelvingRepository;
    private final ExhibitionRepository exhibitionRepository;

    public QRCodeService(ExhibitRepository exhibitRepository, ShelfRepository shelfRepository, ShelvingRepository shelvingRepository, ExhibitionRepository exhibitionRepository) {
        this.exhibitRepository = exhibitRepository;
        this.shelfRepository = shelfRepository;
        this.shelvingRepository = shelvingRepository;
        this.exhibitionRepository = exhibitionRepository;
    }

    public byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public List<GetExhibitWithDescriptionResponse> getExhibitsWithDescriptions(GenerateExhibitQrRequest dto) {
        return exhibitRepository.getExhibitWithDescription(dto);
    }

    public List<NameDescDto> getNamesAndDescriptions(GenerateQrRequest dto) {
        List<NameDescDto> data = new ArrayList<>();
        switch (dto.getQrType()){
            case SHELF -> data = shelfRepository.getNamesAndDescriptions(dto.getIds());
            case SHELVING -> data = shelvingRepository.getNamesAndDescriptions(dto.getIds());
            case EXHIBITION ->data = exhibitionRepository.getNamesAndDescriptions(dto.getIds());
        }
        return data;
    }
}
