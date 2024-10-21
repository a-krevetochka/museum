package com.mgtu.museum.repository.intarface;

import com.mgtu.museum.entity.Exhibition;
import org.springframework.data.repository.CrudRepository;

public interface ExhibitionRepository extends CrudRepository<Exhibition, Integer> {
}
