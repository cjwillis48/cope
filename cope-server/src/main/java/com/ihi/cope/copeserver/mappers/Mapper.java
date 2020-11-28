package com.ihi.cope.copeserver.mappers;

public abstract class Mapper<M, E> {
    public abstract E modelToEntity(M model);
    public abstract M entityToModel(E entity);
}
