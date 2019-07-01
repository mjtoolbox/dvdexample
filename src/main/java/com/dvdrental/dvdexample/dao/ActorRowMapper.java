package com.dvdrental.dvdexample.dao;

import com.dvdrental.dvdexample.entity.Actor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is responsible for mapping from resultSet(DB) to our Entity
 */
public class ActorRowMapper implements RowMapper<Actor> {

    @Override
    public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
        Actor actor = new Actor();
        actor.setActorId(resultSet.getLong("actor_id"));
        actor.setFirstName(resultSet.getString("first_name"));
        actor.setLastName(resultSet.getString("last_name"));
        actor.setLastUpdated(resultSet.getDate("last_update"));
        return actor;
    }
}
