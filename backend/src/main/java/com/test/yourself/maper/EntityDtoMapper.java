package com.test.yourself.maper;

import com.test.yourself.dto.AbstractDto;
import com.test.yourself.model.AbstractEntity;

public interface EntityDtoMapper<E extends AbstractEntity, D extends AbstractDto> {
    E toEntity(D dto);
    D toDto(E entity);
}
