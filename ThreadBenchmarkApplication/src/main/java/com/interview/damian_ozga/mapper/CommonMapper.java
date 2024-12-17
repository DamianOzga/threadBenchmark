package com.interview.damian_ozga.mapper;

/**
 * Common Mapper interface for converting between Entity and DTO objects.
 *
 * @param <Entity> the type of the entity
 * @param <DTO> the type of the data transfer object
 */
public interface CommonMapper<Entity, DTO> {

    /**
     * Converts the given DTO to an Entity.
     *
     * @param dto the data transfer object to convert
     * @return the corresponding entity
     */
    Entity toEntity(DTO dto);

    /**
     * Converts the given Entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the corresponding data transfer object
     */
    DTO toDto(Entity entity);
}