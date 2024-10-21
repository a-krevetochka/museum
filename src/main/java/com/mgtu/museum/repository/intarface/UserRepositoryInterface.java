package com.mgtu.museum.repository.intarface;

import com.mgtu.museum.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryInterface extends CrudRepository<User, Integer> {

}
