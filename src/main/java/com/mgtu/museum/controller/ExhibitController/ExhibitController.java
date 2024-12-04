package com.mgtu.museum.controller.ExhibitController;

import com.mgtu.museum.controller.ExhibitController.request.CreateDescriptionRequest;
import com.mgtu.museum.controller.ExhibitController.request.CreateExhibitRequest;
import com.mgtu.museum.controller.ExhibitController.request.UpdateExhibitRequest;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitDescriptionResponse;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitWithDescriptionResponse;
import com.mgtu.museum.service.ExhibitService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/v1/exhibit")
public class ExhibitController {
    private final ExhibitService exhibitService;

    @PostMapping("create")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> createExhibit(@RequestBody CreateExhibitRequest dto) {
        exhibitService.createExhibit(dto);
        return ResponseEntity.ok("Экспонат создан");
    }

    @PutMapping("/update")
//    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<String> updateExhibit(@RequestBody UpdateExhibitRequest dto) {
        exhibitService.updateExhibit(dto);
        return ResponseEntity.ok("Экспонат обновлен");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GetExhibitResponse> getExhibitById(@PathVariable Integer id) {
        return ResponseEntity.ok(exhibitService.getExhibitById(id));
    }

    @PostMapping("create_description")
    public ResponseEntity<String> createDescription(@RequestBody CreateDescriptionRequest dto) {
        exhibitService.createDescription(dto);
        return ResponseEntity.ok("Описание экспонату создано");
    }

    @GetMapping("get_exhibit_descriptions/{id}")
    public ResponseEntity<List<GetExhibitDescriptionResponse>> getExhibitDescriptions(@PathVariable Integer id) {
        return ResponseEntity.ok(exhibitService.getExhibitDescriptions(id));
    }

    @GetMapping("get_by_name")
    public ResponseEntity<List<GetExhibitResponse>> getExhibitByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(exhibitService.getExhibitByName(name));
    }

    @GetMapping("get_by_receipt_number")
    public ResponseEntity<List<GetExhibitResponse>> getExhibitByReceiptNumber(@PathParam("receipt_number") Integer receiptNumber) throws SQLException {
        return ResponseEntity.ok(exhibitService.getExhibitByReceiptNumber(receiptNumber));
    }

    @GetMapping("get_by_room_id/{id}")
    public ResponseEntity<List<GetExhibitResponse>> getExhibitByRoomId(@PathVariable Integer id) {
        return ResponseEntity.ok(exhibitService.getByRoomId(id));
    }

    @GetMapping("get_by_shelf_id/{id}")
    public ResponseEntity<List<GetExhibitResponse>> getExhibitByShelfId(@PathVariable Integer id) {
        return ResponseEntity.ok(exhibitService.getByShelfId(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<GetExhibitWithDescriptionResponse>> getAll(){
        return ResponseEntity.ok(exhibitService.getAll());
    }
}
