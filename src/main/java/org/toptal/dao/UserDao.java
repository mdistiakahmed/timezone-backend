package org.toptal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.toptal.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    long deleteByEmail(String email);
}