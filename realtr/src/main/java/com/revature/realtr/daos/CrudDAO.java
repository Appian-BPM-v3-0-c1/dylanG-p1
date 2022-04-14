package com.revature.realtr.daos;

import java.util.List;

public interface CrudDAO <T> {

    int save(T obj); //saves any object passed into DB

    List<T> findAll(); //retrieve all tables

    T findById(int id); //locates specific table by id

    List<T> findAllById(int id);

    boolean update(T updatedObj);

    boolean removeById(int user_id);
}
