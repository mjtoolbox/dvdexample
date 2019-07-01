package com.dvdrental.dvdexample.dao;

import com.dvdrental.dvdexample.entity.Actor;

import java.util.List;

// This DAO contains business methods
public interface ActorDao {
    List<Actor> findAll();
    // CRUD methods
    Actor findById(long id);
    Actor createActor(Actor actor);
    Actor updateActor(Actor actor);
    void deleteActor(long id);
}
