package com.interview.damian_ozga.mapper;

public interface CommonMapper<Entity, DTO> {

    Entity toEntity(DTO dto);
    DTO toDto(Entity entity);
}
