package com.revature.realtr.daos;

import java.util.List;

public interface CrudDAO<T> {

    int save(T obj);

    List<T> findAll();

    T findById(int id);

    List<T> findAllById(int id);

    boolean update(T updatedObj);

    boolean removeById(int user_id);
}
