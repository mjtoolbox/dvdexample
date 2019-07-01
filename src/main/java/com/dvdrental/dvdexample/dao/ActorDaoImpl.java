package com.dvdrental.dvdexample.dao;

import com.dvdrental.dvdexample.entity.Actor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ActorDaoImpl implements ActorDao{

    NamedParameterJdbcTemplate template;
    final String findAllQuery = "SELECT * FROM actor";
    final String findByIdQuery = "SELECT * FROM actor WHERE actor_id = :actorid";
    final String insertQuery = "INSERT INTO Actor(first_name, last_name, last_update) VALUES (:firstName, :lastName, :lastUpdate);";
    final String updateQuery = "UPDATE actor SET first_name=:firstName, last_name=:lastName WHERE actor_id= :actorid";
    final String deleteQuery = "DELETE FROM actor WHERE actor_id= :actorid";

    public ActorDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    // In here, we will actually write SQL
    @Override
    public List<Actor> findAll() {
        return template.query( findAllQuery, new ActorRowMapper());
    }

    @Override
    public Actor findById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("actorid", id);
        return template.queryForObject(findByIdQuery, parameters, new ActorRowMapper());
    }

    /**
     * KeyHolder retrieves newly created Actor data in HashMap. Need to reset Actor properties.
     * @param actor
     * @return
     */
    @Override
    public Actor createActor(Actor actor) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", actor.getFirstName())
                .addValue("lastName", actor.getLastName())
                .addValue("lastUpdate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(insertQuery, parameters, keyHolder);
        actor.setActorId( (int)keyHolder.getKeys().get("actor_id"));
        actor.setLastUpdated( (Date) keyHolder.getKeys().get("last_update"));
        return actor;
    }

    /**
     * Update returns status (1=success, 0=fail)
     * UpdateActor method signature changed.
     * @param actor
     * @return
     */
    @Override
    public Actor updateActor(Actor actor) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstName", actor.getFirstName())
                .addValue("lastName", actor.getLastName())
                .addValue("actorid",actor.getActorId());

        int status = template.update(updateQuery, parameters);
        if(status != 0){
            System.out.println("Actor data updated for ID " + actor.getActorId());
        }else{
            System.out.println("No Actor found with ID " + actor.getActorId());
        }
        return actor;
    }

    /**
     * Delete returns status (1=success, 0=fail)
     * @param id
     */
    @Override
    public void deleteActor(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("actorid", id);

        int status = template.update(deleteQuery, parameters);
        if(status != 0){
            System.out.println("Actor deleted successfully");
        }else{
            System.out.println("Actor deletion failed");
        }
    }
}
