package com.seyrancom.consulting.core.repository.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDAO<T extends Serializable> {

    protected final Class<T> ENTITY_CLASS;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        ENTITY_CLASS = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
