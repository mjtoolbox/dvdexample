package com.dvdrental.dvdexample.controller;

import com.dvdrental.dvdexample.entity.Actor;
import com.dvdrental.dvdexample.service.ActorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * This class is responsible for providing REST API endpoint
 * localhost:8080/actors/all
 */
@RestController
public class ActorController {

    @Resource
    ActorService actorService;

    @GetMapping("/actors")
    public List<Actor> findAllActors(){
        return actorService.findAllActors();
    }

    @GetMapping("/actors/{id}")
    public Actor findActorById(@PathVariable long id){
        return actorService.findActorById(id);
    }
    @PostMapping("/actors")
    public Actor createNewActor(@Valid @RequestBody Actor actor){
        return actorService.createNewActor(actor);
    }

    @PutMapping("/actors/{id}")
    public Actor updateActor(@PathVariable long id, @Valid @RequestBody Actor actor){
        return actorService.updateActor(id, actor);
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable long id){
         actorService.deleteActor(id);
    }


}
