package com.example.ims.controllers;

import com.example.ims.models.Product;
import com.example.ims.models.User;
import com.example.ims.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;


    @GetMapping("list")
    public List<User> list(){
        logger.info("Inside the Get Request to list objects");
        return this.userRepository.findAll();
    }

    //@GetMapping
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable int id) {

        logger.info("Inside the Get Request to fetch details of an object");
        User dbUser = userRepository.findById(id).orElse(null);
        if (dbUser != null)
            return dbUser;
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }

    }

    @PostMapping
    public User create(@RequestBody final User user) {
        logger.info("Inside Post Request to create a new object");
        return userRepository.saveAndFlush(user);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public User update(@PathVariable int id, @RequestBody User user) {
        logger.info("Inside the Put Request to update an object");
        User dbUser = userRepository.findById(id).orElse(null);
        if (dbUser != null) {
            BeanUtils.copyProperties(user, dbUser, "id");
            return userRepository.saveAndFlush(dbUser);
        }
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }

    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        logger.info("Inside the Delete Request to delete an object");
        if (userRepository.findById(id).orElse(null) != null)
            userRepository.deleteById(id)   ;
        else
            logger.info("Cannot find the object with the given id");
    }

}
