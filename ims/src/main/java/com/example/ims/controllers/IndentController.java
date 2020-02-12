package com.example.ims.controllers;


import com.example.ims.models.Indent;
import com.example.ims.models.IndentItem;
import com.example.ims.repositories.IndentRepository;
import com.example.ims.service.IndentService;
import com.example.ims.view.IndentView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/indents")
@CrossOrigin
public class IndentController {
    private Logger logger = LoggerFactory.getLogger(IndentController.class);

    @Autowired
    private IndentRepository indentRepository;
    @Autowired
    private IndentService indentService;

    @GetMapping("list")
    public List<IndentView> list(){
        logger.info("Inside the Get Request to list objects");
        List<IndentView> indentView = indentService.list();
        return  indentView;//indentRepository.findAll();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET  )
    public IndentView get(@PathVariable int id){
        logger.info("Inside the Get Request to fetch details of an object");
        return indentService.get(id);
    }


    @PostMapping
    public IndentView create(@RequestBody final IndentView indentView) {
        logger.info("Inside Post Request to create a new object");

        IndentView indent = indentService.createIndent(indentView);
        return (indent);
    }



    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public IndentView update(@PathVariable int id, @RequestBody IndentView indentView) {
        logger.info("Inside the Put Request to update an object");
        return (indentService.updateIndent(id, indentView));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        logger.info("Inside the Delete Request to delete an object");
        if (indentRepository.findById(id).orElse(null) != null)
            indentRepository.deleteById(id);
        else
            logger.info("Cannot find the object with the given id");
    }


}
