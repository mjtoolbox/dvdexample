package com.dvdrental.dvdexample.service;

import com.dvdrental.dvdexample.dao.ActorDao;
import com.dvdrental.dvdexample.entity.Actor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ActorServiceImpl implements ActorService {

    @Resource
    ActorDao actorDao;

    @Override
    public List<Actor> findAllActors() {
        return actorDao.findAll();
    }

    @Override
    public Actor findActorById(long id) {
        return actorDao.findById(id);
    }

    @Override
    public Actor createNewActor(Actor actor) {
        return actorDao.createActor(actor);
    }

    @Override
    public Actor updateActor(long id, Actor actor) {
        actor.setActorId(id);
        return actorDao.updateActor(actor);
    }

    @Override
    public void deleteActor(long id) {
        actorDao.deleteActor(id);
    }
}
