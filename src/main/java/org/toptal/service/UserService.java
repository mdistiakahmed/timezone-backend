package org.toptal.service;

import org.toptal.model.User;
import org.toptal.model.UserDto;
import org.toptal.model.UserUpdate;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<UserDto> findAll();
    User findOne(String username);
    UserDto addUser(UserDto user);
    void bulkDelete(List<String> emailIds);
    User updateUser(UserUpdate userUpdate);
}