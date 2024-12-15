package com.interview.damian_ozga.service.ifc;

public interface GeneralService<T, Key> {

    void save(T documentDTO);

    T getByKey(Key documentKey);

    void deleteAll();

    Boolean existByKey(Key documentKey);
}
