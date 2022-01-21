package org.toptal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.toptal.model.*;
import org.toptal.service.TimeZoneService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/timezones")
public class TimeZoneController {

    @Autowired
    private TimeZoneService timeZoneService;

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value="/addTimeZone", method = RequestMethod.POST)
    public Timezone saveUser(@RequestBody TimeZoneDto timeZoneDto){
        return timeZoneService.addTimeZone(timeZoneDto);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value="/getUserTimeZone", method = RequestMethod.POST)
    public List<Timezone> getUserTimeZone(@RequestBody UserDto user){
        return timeZoneService.getTimeZone(user);
    }


    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value="/deleteTimeZone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTimeZone(@PathVariable Long id){
        System.out.println("here");
        timeZoneService.deleteTimeZone(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @RequestMapping(value="/updateTimeZone", method = RequestMethod.POST)
    public ResponseEntity<?> updateTimeZone(@RequestBody TimeZoneDto timeZoneDto){
        timeZoneService.updateTimeZone(timeZoneDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/getAllTimeZone", method = RequestMethod.GET)
    public List<Timezone> getAllTimeZone(){
        return timeZoneService.getAllTimeZone();
    }





}
