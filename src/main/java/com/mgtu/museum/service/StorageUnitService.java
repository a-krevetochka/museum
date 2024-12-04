package com.mgtu.museum.service;

import com.mgtu.museum.controller.StorageUnitController.Request.*;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelfsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetShelvingsResponse;
import com.mgtu.museum.controller.StorageUnitController.Response.GetRoomResponse;
import com.mgtu.museum.entity.Room;
import com.mgtu.museum.entity.Shelf;
import com.mgtu.museum.entity.Shelving;
import com.mgtu.museum.repository.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StorageUnitService {
    private final ShelfRepository shelfRepository;
    private final ShelvingRepository shelvingRepository;
    private final RoomRepository roomRepository;
    private final ExhibitionExhibitRepository exhibitionExhibitRepository;
    private final ModelMapper mapper;

    public void createShelving(CreateShelvingRequest dto) {
        Shelving shelving = Shelving.builder()
                .roomId(dto.getRoomId())
                .number(dto.getShelvingNumber())
                .description(dto.getDescription())
                .build();
        shelvingRepository.save(shelving);
    }

    public void createShelf(CreateShelfRequest dto) {
        Shelf shelf = Shelf.builder()
                .shelvingId(dto.getShelvingId())
                .number(dto.getShelfNumber())
                .description(dto.getDescription())
                .build();
        shelfRepository.save(shelf);
    }

    public void deleteShelf(Integer shelfId) {
        Boolean isHasExhibits = exhibitionExhibitRepository.isShelfEmpty(shelfId);
        if (isHasExhibits) {
            throw new IllegalArgumentException("В полке есть экспонаты");
        }
        shelfRepository.delete(shelfId);
    }

    public void deleteShelving(Integer shelvingId) {
        Boolean isHasExhibits = exhibitionExhibitRepository.isShelvingEmpty(shelvingId);
        if (isHasExhibits) {
            throw new IllegalArgumentException("В полке есть экспонаты");
        }
        shelvingRepository.deleteById(shelvingId);
    }

    public List<GetRoomResponse> getAllRooms() {
        return roomRepository.getAll().stream().map(r -> mapper.map(r, GetRoomResponse.class)).toList();
    }

    public List<GetShelvingsResponse> getShelvings(GetShelvingsRequest dto) {
        return shelvingRepository.findAllByRoom(dto.getRoomId());
    }

    public List<GetShelfsResponse> getShelfs(Integer shelvingId) {
        return shelfRepository.findAllByShelvingId(shelvingId).stream().map(s -> mapper.map(s, GetShelfsResponse.class)).toList();
    }

    public void createRoom(CreateRoomRequest dto) {
        roomRepository.save(Room.builder()
                .number(dto.getRoomNumber())
                .build());
    }

    public void changeShelvingRoom(ChangeShelvingRoom dto) {
        Boolean isHasExhibits = exhibitionExhibitRepository.isShelvingEmpty(dto.getShelvingId());
        if (isHasExhibits) {
            throw new IllegalArgumentException("В полке есть экспонаты");
        }
        shelvingRepository.updateRoom(dto.getShelvingId(), dto.getRoomId());
    }

    public void updateShelf(UpdateShelfRequest dto) {
        shelfRepository.updateDescription(dto);
    }

    public void updateShelving(UpdateShelvingRequest dto) {
        shelvingRepository.updateDescription(dto.getShelvingId(), dto.getDescription());
    }

    public List<GetRoomResponse> getRoomsByExhibitionId(Integer exhibitionId) {
       return roomRepository.getRoomsByExibitionId(exhibitionId).stream().map(r -> mapper.map(r, GetRoomResponse.class)).toList();
    }
}
