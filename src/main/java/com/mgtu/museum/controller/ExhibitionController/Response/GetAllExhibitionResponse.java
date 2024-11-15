package com.mgtu.museum.controller.ExhibitionController.Response;


import com.mgtu.museum.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllExhibitionResponse {
    private Integer id;
    private String name;
    private String description;
    private Date dateFrom;
    private Date dateTo;
    private User creator;
}
