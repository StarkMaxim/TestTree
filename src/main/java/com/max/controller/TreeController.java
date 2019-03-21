package com.max.controller;

import com.max.Entity.TreeElement;
import com.max.service.TreeElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreeController {

    @Autowired
    private TreeElementService treeElementService;


    @GetMapping("/tree/{id}")
    public ResponseEntity<TreeElement> getTreeElementById(@PathVariable(value = "id") int id){
        TreeElement treeElement = treeElementService.getTreeElementById(id);
        return ResponseEntity.ok().body(treeElement);
    }
    @GetMapping("/tree")
    public ResponseEntity<Object> getAllTreeElement(){
        List<TreeElement> treeElement = treeElementService.getAllTreeElements();
        return ResponseEntity.ok().body(treeElement);
    }

    @GetMapping("/trees/{id}")
    public ResponseEntity<Object> getAllTreeElementById(@PathVariable(value = "id") int id){
        List<TreeElement> treeElement = treeElementService.getAllTreeElementsById(id);
        return ResponseEntity.ok().body(treeElement);
    }

    @PostMapping("/tree")
    public TreeElement addTreeElement(@RequestBody TreeElement treeElement){
        return treeElementService.saveTreeElement(treeElement);
    }


}
