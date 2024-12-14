package com.interview.damian_ozga.service.ifc;

import java.util.List;

public interface IGeneralService<T, ID> {

    void save(T entity);

    T getById(ID entityId);

    List<T> getAll();
}
