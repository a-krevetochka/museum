package com.mgtu.museum.service;

import com.mgtu.museum.controller.StorageUnitController.Request.CreateShelfRequest;
import com.mgtu.museum.controller.StorageUnitController.Request.CreateShelvingRequest;
import com.mgtu.museum.controller.StorageUnitController.Request.GetShelvingsRequest;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelfsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.RetRoomResponse;
import com.mgtu.museum.repository.RoomRepository;
import com.mgtu.museum.repository.ShelfRepository;
import com.mgtu.museum.repository.ShelvingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StorageUnitService {
    private final ShelfRepository shelfRepository;
    private final ShelvingRepository shelvingRepository;
    private final RoomRepository roomRepository;


    public void createShelving(CreateShelvingRequest dto) {

    }

    public void createShelf(Integer id, CreateShelfRequest dto) {
    }

    public void deleteShelf(Integer shelfId) {
    }

    public void deleteShelving(Integer shelvingId) {
    }

    public List<RetRoomResponse> getAllRooms() {
        return null;
    }

    public List<GetShelvingsResponse> getShelvings(GetShelvingsRequest dto) {
        return null;
    }

    public List<GetShelfsResponse> getShelfs(Integer shelvingId) {
        return null;
    }
}
