package dao;

import java.util.List;

public interface DAO<T> {
    void insert(T model);
    void update(T model);
    void delete(int id);
    List<T> getAll();
    T getById(int id);
}