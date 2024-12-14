package com.interview.damian_ozga.config.ifc;

public interface IDatabaseConnection<T> {

    T connect();

    void disconnect();
}
