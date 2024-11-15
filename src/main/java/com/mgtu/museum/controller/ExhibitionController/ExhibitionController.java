package com.mgtu.museum.controller.ExhibitionController;

import com.mgtu.museum.controller.ExhibitionController.Request.CreateExhibitionRequest;
import com.mgtu.museum.controller.ExhibitionController.Response.GetAllExhibitionResponse;
import com.mgtu.museum.controller.ExhibitionController.Request.UpdateExhibitionRequest;
import com.mgtu.museum.service.ExhibitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exhibition")
@AllArgsConstructor
public class ExhibitionController {
    private final ExhibitionService exhibitionService;

    @PostMapping("create")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> createExhibition(@RequestBody CreateExhibitionRequest dto) {
        exhibitionService.createExhibition(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Успешно создано");
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> updateExhibition(@RequestBody UpdateExhibitionRequest dto) {
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
    public ResponseEntity<List<GetAllExhibitionResponse>> getAllExhibitions() {
        return ResponseEntity.status(HttpStatus.OK).body(exhibitionService.getAllExhibitions());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<GetAllExhibitionResponse> getExhibitionById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(exhibitionService.getExhibitionById(id));
    }
}
