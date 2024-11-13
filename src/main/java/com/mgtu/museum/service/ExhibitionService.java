package com.mgtu.museum.service;

import com.mgtu.museum.controller.ExhibitionController.dto.CreateExhibitionDto;
import com.mgtu.museum.controller.ExhibitionController.dto.GetExhibitionDto;
import com.mgtu.museum.controller.ExhibitionController.dto.UpdateExhibitionDto;
import com.mgtu.museum.repository.ExhibitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ExhibitionService {
    private final ExhibitionRepository exhibitionRepository;

    public void createExhibition(CreateExhibitionDto dto) {
        validateDates(dto.getStartDate(), dto.getEndDate());
        exhibitionRepository.save(dto);
    }


    public List<GetExhibitionDto> getAllExhibitions() {
        return null;
    }

    public GetExhibitionDto getExhibitionById(int id) {
        return null;
    }

    public void updateExhibition(UpdateExhibitionDto dto) {
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

}
