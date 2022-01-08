package org.toptal.service;

import org.toptal.model.Timezone;
import org.toptal.model.TimeZoneDto;
import org.toptal.model.UserDto;

import java.util.List;

public interface TimeZoneService {
    Timezone addTimeZone (TimeZoneDto timeZoneDto);
    List<Timezone> getTimeZone (UserDto userDto);
    List<Timezone> getAllTimeZone ();
    void deleteTimeZone (Long id);
    void updateTimeZone (TimeZoneDto timeZoneDto);
}
