package com.dvdrental.dvdexample.service;

import com.dvdrental.dvdexample.entity.Actor;

import java.util.List;

/**
 * This interface holds business methods
 */
public interface ActorService {
    public List<Actor> findAllActors();

    // CRUD methods
    public Actor findActorById(long id);

    public Actor createNewActor(Actor actor);

    public Actor updateActor(long id, Actor actor);

    public void deleteActor(long id);
}
