package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.core.domain.entity.AbstractEntity;

//@MappedSuperclass
//@Table(name = "employee", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = "E_MAIL"))
//@Table(name = "employee")
public abstract class EmployeeBase extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public EmployeeBase() {
    }
}
