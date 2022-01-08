package org.toptal.service;

import org.toptal.model.User;
import org.toptal.model.UserDto;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}