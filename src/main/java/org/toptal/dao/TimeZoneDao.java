package org.toptal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.toptal.model.Timezone;
import org.toptal.model.User;

import java.util.List;

@Repository
public interface TimeZoneDao extends CrudRepository<Timezone, Long> {
    List<Timezone> findByUser(User user);
}
