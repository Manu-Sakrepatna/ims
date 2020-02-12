package com.example.ims.controllers;

import com.example.ims.models.Indent;
import com.example.ims.models.IndentItem;
import com.example.ims.repositories.IndentItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/indent_items")
@CrossOrigin
public class IndentItemController {
    private Logger logger = LoggerFactory.getLogger(IndentItemController.class);

    @Autowired
    private IndentItemRepository indentItemRepository;

    @GetMapping("list")
    public List<IndentItem> list(){
        logger.info("Inside the Get Request to list objects");
        return indentItemRepository.findAll();
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET  )
    public IndentItem get(@PathVariable int id){
        logger.info("Inside the Get Request to fetch details of an object");
        IndentItem dbIndentItem = indentItemRepository.findById(id).orElse(null);
        if (dbIndentItem != null)
            return dbIndentItem;
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }
    }


    @PostMapping
    public IndentItem create(@RequestBody final IndentItem indentItem) {
        logger.info("Inside Post Request to create a new object");
        return indentItemRepository.saveAndFlush(indentItem);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public IndentItem update(@PathVariable int id, @RequestBody IndentItem indentItem) {
        logger.info("Inside the Put Request to update an object");
        IndentItem dbIndentItem = indentItemRepository.findById(id).orElse(null);
        if (dbIndentItem != null) {
            BeanUtils.copyProperties(indentItem, dbIndentItem, "id");
            return indentItemRepository.saveAndFlush(dbIndentItem);
        }
        else{
            logger.info("Cannot find the object with the given id");
            return null;
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        logger.info("Inside the Delete Request to delete an object");
        if (indentItemRepository.findById(id).orElse(null) != null)
            indentItemRepository.deleteById(id)   ;
        else
            logger.info("Cannot find the object with the given id");
    }




}

