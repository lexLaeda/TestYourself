package com.test.yourself.mapper;

import com.test.yourself.dto.AbstractDto;
import com.test.yourself.model.AbstractEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<E extends AbstractEntity, D extends AbstractDto> implements EntityDtoMapper<E,D>{

    private ModelMapper modelMapper;

    private Class<E> entityClass;

    private Class<D> dtoClass;

    AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }
    @Autowired
    public void setMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto,entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, dtoClass);
    }
    Converter<E,D> toDtoConverter(){
        return mappingContext -> {
            E source = mappingContext.getSource();
            D destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }
    Converter<D,E> toEntityConverter(){
        return mappingContext -> {
            D source = mappingContext.getSource();
            E destination = mappingContext.getDestination();
            mapSpecificFields(source,destination);
            return mappingContext.getDestination();
        };
    }

    public void mapSpecificFields(E source, D destination){

    }
    public void mapSpecificFields(D source, E destination){

    }
}
