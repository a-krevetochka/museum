package com.mgtu.museum.service;

import com.mgtu.museum.controller.ExhibitController.request.CreateDescriptionRequest;
import com.mgtu.museum.controller.ExhibitController.request.CreateExhibitRequest;
import com.mgtu.museum.controller.ExhibitController.request.UpdateExhibitRequest;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitDescriptionResponse;
import com.mgtu.museum.controller.ExhibitController.response.GetExhibitResponse;
import com.mgtu.museum.entity.Exhibit;
import com.mgtu.museum.entity.ExhibitDescription;
import com.mgtu.museum.repository.ExhibitDescriptionRepository;
import com.mgtu.museum.repository.ExhibitRepository;
import com.mgtu.museum.repository.ShelfRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExhibitService {
    private final ShelfRepository shelfRepository;
    private final ExhibitRepository exhibitRepository;
    private final ModelMapper modelMapper;
    private final ExhibitDescriptionRepository exhibitDescriptionRepository;

    public void createExhibit(CreateExhibitRequest dto) {
        Exhibit exhibit = Exhibit.builder()
                .name(dto.getExhibitName())
                .build();
        exhibitRepository.save(exhibit);

    }

    public void updateExhibit(UpdateExhibitRequest dto) {
        Exhibit exhibit = Exhibit.builder()
                .id(dto.getExhibitId())
                .name(dto.getExhibitName())
                .build();
        exhibitRepository.update(exhibit);
    }

    public GetExhibitResponse getExhibitById(Integer id) {
        return modelMapper.map(exhibitRepository.findById(id), GetExhibitResponse.class);
    }

    public void deleteById(Integer id) {
    }

    public void createDescription(CreateDescriptionRequest dto) {
            exhibitDescriptionRepository.save(modelMapper.map(dto, ExhibitDescription.class));
    }

    public List<GetExhibitDescriptionResponse> getExhibitDescriptions(Integer id) {
        return exhibitDescriptionRepository.getAllByExhibitId(id).stream().map(desc -> modelMapper.map(desc, GetExhibitDescriptionResponse.class)).toList();
    }
}
