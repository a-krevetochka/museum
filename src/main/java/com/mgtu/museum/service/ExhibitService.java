package com.mgtu.museum.service;

import com.mgtu.museum.controller.ExhibitController.request.CreateExhibitRequest;
import com.mgtu.museum.controller.ExhibitController.request.UpdateExhibitRequest;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.entity.Exhibit;
import com.mgtu.museum.repository.ExhibitRepository;
import com.mgtu.museum.repository.ShelfRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExhibitService {
    private final ShelfRepository shelfRepository;
    private final ExhibitRepository exhibitRepository;
    private final ModelMapper modelMapper;

    public void createExhibit(CreateExhibitRequest dto) {
        Exhibit exhibit = new Exhibit().builder()
                .name(dto.getExhibitName())
                .storageShelfId(dto.getStorageShelfId())
                .packagingType(dto.getPackagingType())
                .build();
        exhibitRepository.save(exhibit);

    }

    public void updateExhibit(UpdateExhibitRequest dto) {
        Exhibit exhibit = new Exhibit().builder()
                .id(dto.getExhibitId())
                .name(dto.getExhibitName())
                .storageShelfId(dto.getStorageShelfId())
                .packagingType(dto.getPackagingType())
                .build();
        exhibitRepository.update(exhibit);
    }

    public GetExhibitResponse getExhibitById(Integer id) {
        return modelMapper.map(exhibitRepository.findById(id), GetExhibitResponse.class);
    }

    public void deleteById(Integer id) {
    }
}
