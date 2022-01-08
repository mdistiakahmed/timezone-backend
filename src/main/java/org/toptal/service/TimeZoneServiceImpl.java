package org.toptal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.toptal.dao.TimeZoneDao;
import org.toptal.dao.UserDao;
import org.toptal.model.Timezone;
import org.toptal.model.TimeZoneDto;
import org.toptal.model.User;
import org.toptal.model.UserDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TimeZoneServiceImpl implements TimeZoneService{

    @Autowired
    private TimeZoneDao timeZoneDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Timezone addTimeZone (TimeZoneDto timeZoneDto) {
        //return timeZoneDao.save(timeZoneDto);
        Timezone timeZone = new Timezone();
        timeZone.setName(timeZoneDto.getName());
        timeZone.setCity(timeZoneDto.getCity());
        timeZone.setHourdiff(timeZoneDto.getHourDiff());
        timeZone.setMindiff(timeZoneDto.getMinDiff());

        User user = userDao.findByEmail(timeZoneDto.getEmail());
        timeZone.setUser(user);

        return timeZoneDao.save(timeZone);
    }

    @Override
    public List<Timezone> getTimeZone (UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail());
        List<Timezone> result = timeZoneDao.findByUser(user);

        return result;
    }

    @Override
    public void deleteTimeZone (Long id) {
        timeZoneDao.deleteById(id);
    }

    @Override
    public void updateTimeZone (TimeZoneDto timeZoneDto) {
        Long id = timeZoneDto.getId().longValue();
        Optional<Timezone> timezoneOptional = timeZoneDao.findById(id);
        if(timezoneOptional.isPresent()) {
            Timezone timezone = timezoneOptional.get();
            timezone.setName(timeZoneDto.getName());
            timezone.setCity(timeZoneDto.getCity());
            timezone.setHourdiff(timeZoneDto.getHourDiff());
            timezone.setMindiff(timeZoneDto.getMinDiff());

            timeZoneDao.save(timezone);
        }
    }

    @Override
    public List<Timezone> getAllTimeZone () {
        List<Timezone> result= new ArrayList<>();
        timeZoneDao.findAll().iterator().forEachRemaining(result::add);
        return result;
    }
}
