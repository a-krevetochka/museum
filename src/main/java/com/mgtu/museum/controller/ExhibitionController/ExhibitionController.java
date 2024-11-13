package com.mgtu.museum.controller.ExhibitionController;

import com.mgtu.museum.controller.ExhibitionController.dto.CreateExhibitionDto;
import com.mgtu.museum.controller.ExhibitionController.dto.GetExhibitionDto;
import com.mgtu.museum.controller.ExhibitionController.dto.UpdateExhibitionDto;
import com.mgtu.museum.service.ExhibitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/exhibition")
@AllArgsConstructor
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @PostMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> createExhibition(@RequestBody CreateExhibitionDto dto) {
        exhibitionService.createExhibition(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Успешно создано");
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> updateExhibition(@RequestBody UpdateExhibitionDto dto) {
        exhibitionService.updateExhibition(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Успешно обновлено");


    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> deleteExhibition(@PathVariable("id") Integer id) {
        exhibitionService.deleteExhibition(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetExhibitionDto>> getAllExhibitions() {
        return ResponseEntity.status(HttpStatus.OK).body(exhibitionService.getAllExhibitions());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<GetExhibitionDto> getExhibitionById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(exhibitionService.getExhibitionById(id));
    }
}
