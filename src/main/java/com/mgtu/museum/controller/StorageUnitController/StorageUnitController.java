package com.mgtu.museum.controller.StorageUnitController;

import com.mgtu.museum.controller.StorageUnitController.Request.*;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelfsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetRoomResponse;
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
        return ResponseEntity.ok("Витрина создана");
    }

    @PostMapping("create_room")
    public ResponseEntity<String> createRoom(@RequestBody CreateRoomRequest dto){
        storageUnitService.createRoom(dto);
        return ResponseEntity.ok("Комната создана");
    }

    @PostMapping("create_shelf")
    public ResponseEntity<String> createShelf(@RequestBody CreateShelfRequest dto) {
        storageUnitService.createShelf(dto);
        return ResponseEntity.ok("полка успешно создана");
    }

    @DeleteMapping("delete_shelf/{shelfId}")
    public ResponseEntity<String> deleteShelf(@PathVariable Integer shelfId) {
        storageUnitService.deleteShelf(shelfId);
        return ResponseEntity.ok("полка успешно удалена");
    }

    @PutMapping("/update_shelf")
    public ResponseEntity<String> updateShelf(@RequestBody UpdateShelfRequest dto){
        storageUnitService.updateShelf(dto);
        return ResponseEntity.ok("полка успешно обновлена");
    }

    @PutMapping("/update_shelving")
    public ResponseEntity<String> updateShelving(@RequestBody UpdateShelvingRequest dto){
        storageUnitService.updateShelving(dto);
        return ResponseEntity.ok("Витрина успешно обновлена");
    }

    @DeleteMapping("delete_shelving/{shelvingId}")
    public ResponseEntity<String> deleteShelving(@PathVariable Integer shelvingId) {
        storageUnitService.deleteShelving(shelvingId);
        return ResponseEntity.ok("Шкаф успешно удален");
    }

    @GetMapping("get_all_rooms")
    public ResponseEntity<List<GetRoomResponse>> getAllRooms() {
        return ResponseEntity.ok(storageUnitService.getAllRooms());
    }

    @GetMapping("get_rooms_by_exhibition_id/{exhibitionId}")
    public ResponseEntity<List<GetRoomResponse>> getRoomsByExhibitionId(@PathVariable Integer exhibitionId) {
        return ResponseEntity.ok(storageUnitService.getRoomsByExhibitionId(exhibitionId));
    }

    @PutMapping("change_shelving_room")
    public ResponseEntity<String> changeShelvingRoom(@RequestBody ChangeShelvingRoom dto){
        storageUnitService.changeShelvingRoom(dto);
        return ResponseEntity.ok("Витрина перемещена");
    }

    @GetMapping("get_shelvings/{id}")
    public ResponseEntity<List<GetShelvingsResponse>> getShelvings(@PathVariable Integer id) {
        return ResponseEntity.ok(storageUnitService.getShelvings(id));
    }

    @GetMapping("shelving/{shelvingId}/get_shelfs")
    public ResponseEntity<List<GetShelfsResponse>> getShelfsByShelvingId(@PathVariable Integer shelvingId) {
        return ResponseEntity.ok(storageUnitService.getShelfs(shelvingId));
    }
}
