package com.mgtu.museum.controller.StorageUnitController;

import com.mgtu.museum.controller.StorageUnitController.Request.CreateShelfRequest;
import com.mgtu.museum.controller.StorageUnitController.Request.CreateShelvingRequest;
import com.mgtu.museum.controller.StorageUnitController.Request.GetShelvingsRequest;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelfsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.RetRoomResponse;
import com.mgtu.museum.service.StorageUnitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/storage_unit")
@AllArgsConstructor
public class StorageUnitController {
    private final StorageUnitService storageUnitService;

    @PostMapping("create_shelving")
    public ResponseEntity<String> createShelving(@RequestBody CreateShelvingRequest dto) {
        storageUnitService.createShelving(dto);
        return ResponseEntity.ok(dto.getType() + " успешно создан/а");
    }

    @PostMapping("shelf/{id}/create_shelf")
    public ResponseEntity<String> createShelf(@PathVariable Integer id, @RequestBody CreateShelfRequest dto) {
        storageUnitService.createShelf(id, dto);
        return ResponseEntity.ok("полка успешно создана");
    }

    @DeleteMapping("delete_shelf/{shelfId}")
    public ResponseEntity<String> deleteShelf(@PathVariable Integer shelfId) {
        storageUnitService.deleteShelf(shelfId);
        return ResponseEntity.ok("полка успешно удалена");
    }

    @DeleteMapping("delete_shelving/{shelvingId}")
    public ResponseEntity<String> deleteShelving(@PathVariable Integer shelvingId) {
        storageUnitService.deleteShelving(shelvingId);
        return ResponseEntity.ok("Шкаф успешно удален");
    }

    @GetMapping("get_all_rooms")
    public ResponseEntity<List<RetRoomResponse>> getAllRooms() {
        return ResponseEntity.ok(storageUnitService.getAllRooms());
    }

    @GetMapping("shelvings")
    public ResponseEntity<List<GetShelvingsResponse>> getShelvings (@RequestBody GetShelvingsRequest dto) {
        return ResponseEntity.ok(storageUnitService.getShelvings(dto));
    }

    @GetMapping("shelving/{shelvingId}/get_shelfs")
    public ResponseEntity<List<GetShelfsResponse>> getShelfsByShelvingId(@PathVariable Integer shelvingId) {
        return ResponseEntity.ok(storageUnitService.getShelfs(shelvingId));
    }
}
