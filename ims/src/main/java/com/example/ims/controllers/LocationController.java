package com.example.ims.controllers;

import com.example.ims.models.Indent;
import com.example.ims.models.Location;
import com.example.ims.models.Product;
import com.example.ims.repositories.LocationRepository;
import com.example.ims.view.IndentView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
@CrossOrigin
public class LocationController {

    private Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("list")
    public List<Location> list(){
        logger.info("Inside the Get Request to list objects");
        return locationRepository.findAll();
    }

    @RequestMapping(value ="{id}", method = RequestMethod.GET)
    public Location get(@PathVariable int id){
        logger.info("Inside the Get Request to fetch details of an object");
        Location location = locationRepository.findById(id).orElse(null);

        if (location != null)
            return location;
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }


    @PostMapping
    public Location create(@RequestBody final Location location) {
        logger.info("Inside Post Request to create a new object");
        return locationRepository.saveAndFlush(location);
    }



    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Location update(@PathVariable int id, @RequestBody Location location) {
        logger.info("Inside the Put Request to update an object");
        Location dbLocation = locationRepository.findById(id).orElse(null);
        if (dbLocation != null) {
            BeanUtils.copyProperties(location, dbLocation, "id");
            return locationRepository.saveAndFlush(dbLocation);
        }
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }

    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        logger.info("Inside the Delete Request to delete an object");
        if (locationRepository.findById(id).orElse(null) != null)
            locationRepository.deleteById(id)   ;
        else
            logger.info("Cannot find the object with the given id");
    }



}
