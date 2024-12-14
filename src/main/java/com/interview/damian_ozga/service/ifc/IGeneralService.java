package com.interview.damian_ozga.service.ifc;

public interface IGeneralService<T, ID> {

    void save(T entity);

    T getById(ID entityId);

    void deleteAll();
}
