package com.mgtu.museum.service;

import com.mgtu.museum.controller.ExhibitionController.Request.AddExhibitRequest;
import com.mgtu.museum.controller.ExhibitionController.Request.AddRoomRequest;
import com.mgtu.museum.controller.ExhibitionController.Request.CreateExhibitionRequest;
import com.mgtu.museum.controller.ExhibitionController.Response.GetAllExhibitionResponse;
import com.mgtu.museum.controller.ExhibitionController.Request.UpdateExhibitionRequest;
import com.mgtu.museum.entity.Exhibition;
import com.mgtu.museum.entity.ExhibitionExhibit;
import com.mgtu.museum.entity.ExhibitionRoom;
import com.mgtu.museum.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;
    private final ModelMapper modelMapper;
    private final ExhibitionExhibitRepository exhibitionExhibitRepository;
    private final RoomRepository roomRepository;
    private final ShelfRepository shelfRepository;
    private final ExhibitionRoomRepository exhibitionRoomRepository;

    public void createExhibition(CreateExhibitionRequest dto) {
        validateDates(dto.getStartDate(), dto.getEndDate());
        exhibitionRepository.save(dto);
    }


    public List<GetAllExhibitionResponse> getAllExhibitions() {
        return exhibitionRepository.getAll().stream().map(e -> modelMapper.map(e, GetAllExhibitionResponse.class)).toList();
    }

    public GetAllExhibitionResponse getExhibitionById(int id) {
        return modelMapper.map(exhibitionRepository.getById(id), GetAllExhibitionResponse.class);
    }

    public void updateExhibition(UpdateExhibitionRequest dto) {
        validateDates(dto.getStartDate(), dto.getEndDate());
        exhibitionRepository.update(dto);
    }

    public void deleteExhibition(Integer id) {
        Boolean isHasExhibits = exhibitionExhibitRepository.hasExhibits(id);
        if (isHasExhibits) {
            throw new IllegalArgumentException("Выставка уже используется");
        }
        exhibitionRepository.delete(id);
    }

    private void validateDates(Date startDate, Date endDate) {
        if (endDate.before(Date.from(Instant.now()))) {
            throw new IllegalArgumentException("Выставка не может закончится в прошлом");
        }
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("Выставка не может начаться позже ее окончания");
        }
    }

    public void addExhibit(AddExhibitRequest dto) {
        if (dto.getShelfId() == null && dto.getRoomId() == null){
            throw new IllegalArgumentException("Выберите место для размещение объекта");
        }
        boolean isAtAnotherExhibition = exhibitionExhibitRepository.isExhibitOnExhibition(dto.getExhibitId());
        if (isAtAnotherExhibition){
            throw new IllegalArgumentException("Данный экспонат уже используется на другой выставке");
        }
        Integer exhibitionId = dto.getShelfId() == null ? exhibitionRoomRepository.getExhibitionIdByRoomId(dto.getRoomId()) :
                shelfRepository.getExhibitionIdByShelfId(dto.getShelfId());
        if (exhibitionId == null){
            throw new EntityNotFoundException("Выставки с этой полкой/помещением не существует");
        }
        exhibitionExhibitRepository.save(ExhibitionExhibit.builder()
                .exhibitionId(exhibitionId)
                .descriptionId(dto.getDescriptionId())
                .exhibitId(dto.getExhibitId())
                .shelfId(dto.getShelfId())
                .build());
    }

    public void addRoom(AddRoomRequest dto) {
        exhibitionRoomRepository.save(ExhibitionRoom.builder()
                .roomId(dto.getRoomId())
                .exhibitionId(dto.getExhibitionId())
                .build());
    }
}
