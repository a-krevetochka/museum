package com.mgtu.museum.controller.QrController;

import com.itextpdf.io.font.constants.StandardFonts;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitWithDescriptionResponse;
import com.mgtu.museum.controller.QrController.dto.NameDescDto;
import com.mgtu.museum.controller.QrController.request.GenerateExhibitQrRequest;
import com.mgtu.museum.controller.QrController.request.GenerateQrRequest;
import com.mgtu.museum.service.ExhibitService;
import com.mgtu.museum.service.QRCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import com.itextpdf.kernel.font.PdfFontFactory;
import java.util.List;
import com.itextpdf.text.pdf.BaseFont;

@RestController
@RequestMapping("api/v1/qr")
@AllArgsConstructor
public class QrController {

    private QRCodeService qrCodeService;

    @PostMapping("/generate_exhibits_qr")
    public ResponseEntity<byte[]> generateExhibitsQr(@RequestBody GenerateExhibitQrRequest dto) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, pdfOutputStream);
        document.open();
        List<GetExhibitWithDescriptionResponse> exhibits = qrCodeService.getExhibitsWithDescriptions(dto);
        BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12);
        for (GetExhibitWithDescriptionResponse exhibit : exhibits) {
            // Генерация QR-кода
            byte[] qrCodeImage = qrCodeService.generateQRCodeImage(exhibit.getDescription(), 200, 200);
            Image qrCode = Image.getInstance(qrCodeImage);
            document.add(qrCode);
            Paragraph paragraph = new Paragraph(exhibit.getExhibitName().trim(), font);
            document.add(paragraph);
            document.add(new Paragraph("\n")); // Добавить отступ
        }

        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exhibits.pdf");
        return new ResponseEntity<>(pdfOutputStream.toByteArray(), headers, HttpStatus.OK);
    }
    @PostMapping("/generate_qr")
    public ResponseEntity<byte[]> generateQr(@RequestBody GenerateQrRequest dto) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, pdfOutputStream);
        document.open();

        List<NameDescDto> data = qrCodeService.getNamesAndDescriptions(dto);
        BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont, 12);

        for (NameDescDto element : data) {
            // Генерация QR-кода
            byte[] qrCodeImage = qrCodeService.generateQRCodeImage(element.getDesc(), 200, 200);
            Image qrCode = Image.getInstance(qrCodeImage);
            document.add(qrCode);

            // Добавление текста с использованием установленного шрифта
            Paragraph paragraph = new Paragraph(element.getName().trim(), font);
            document.add(paragraph);

            // Добавление отступа
            document.add(new Paragraph("\n"));
        }

        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exhibits.pdf");
        return new ResponseEntity<>(pdfOutputStream.toByteArray(), headers, HttpStatus.OK);
    }
}
