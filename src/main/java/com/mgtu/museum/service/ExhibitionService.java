package com.mgtu.museum.service;

import com.mgtu.museum.controller.ExhibitionController.Request.AddExhibitRequest;
import com.mgtu.museum.controller.ExhibitionController.Request.CreateExhibitionRequest;
import com.mgtu.museum.controller.ExhibitionController.Response.GetAllExhibitionResponse;
import com.mgtu.museum.controller.ExhibitionController.Request.UpdateExhibitionRequest;
import com.mgtu.museum.entity.ExhibitionExhibit;
import com.mgtu.museum.repository.ExhibitionExhibitRepository;
import com.mgtu.museum.repository.ExhibitionRepository;
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

    public void createExhibition(CreateExhibitionRequest dto) {
        validateDates(dto.getStartDate(), dto.getEndDate());
        exhibitionRepository.save(dto);
    }


    public List<GetAllExhibitionResponse> getAllExhibitions() {
        return exhibitionRepository.getAll().stream().map(e -> modelMapper.map(e, GetAllExhibitionResponse.class)).toList();
    }

    public GetAllExhibitionResponse getExhibitionById(int id) {
        return null;
    }

    public void updateExhibition(UpdateExhibitionRequest dto) {
        validateDates(dto.getStartDate(), dto.getEndDate());
        exhibitionRepository.update(dto);
    }

    public void deleteExhibition(Integer id) {
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
        exhibitionExhibitRepository.save(ExhibitionExhibit.builder()
                .exhibitionId(dto.getExhibitionId())
                .descriptionId(dto.getDescriptionId())
                .exhibitId(dto.getExhibitId())
                .shelfId(dto.getShelfId())
                .build());
    }
}
