package com.med.disease.tracking.app.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.med.disease.tracking.app.exception.DatabaseException;
import com.med.disease.tracking.app.model.Location;

@Repository
public class LocationDAO extends BaseDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationDAO.class);

    public Location fetchLocationForUser(Location location) throws Exception{
        try{
             return getSqlSession().selectOne("Location.getLocationForUser", location);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }

    public int addLocation(Location location) throws Exception{
        try{
            return getSqlSession().insert("Location.insertLocation", location);
        } catch (Exception exception){
            LOGGER.error("Database Exception :{}", exception.getMessage());
            throw new DatabaseException(exception.getMessage());
        }
    }
}
