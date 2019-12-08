package com.pozharsky.dmitri.repository;

public interface BaseRepository<T> {

    void save(T entity);

}
