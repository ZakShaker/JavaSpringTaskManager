package com.zaksh.model.dao.interfaces;

import java.util.List;

public interface DAO<E, PublicKey> {

    E getById(PublicKey id);

    List<E> getAll();

    E save(E entity);

    PublicKey insert(E entity);

    int update(E entity);

    int delete(E entity);
}