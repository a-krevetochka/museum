package com.mgtu.museum.repository.intarface;

import com.mgtu.museum.entity.Exhibit;
import org.springframework.data.repository.CrudRepository;

public interface ExhibitRepository extends CrudRepository<Exhibit, Integer> {
}
