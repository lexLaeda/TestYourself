package com.test.yourself.mapper;

import com.test.yourself.dto.AbstractDto;
import com.test.yourself.model.AbstractEntity;

public interface EntityDtoMapper<E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);
    D toDto(E entity);
    void initMapper();
}
