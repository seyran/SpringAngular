package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.core.domain.entity.AbstractEntity;

import javax.persistence.*;

/*@Entity
@Table(name="customer")*/
public class Customer extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    protected String name;

    @AttributeOverrides({
            @AttributeOverride(name = "state",
                    column = @Column(name = "ADDR_STATE")),
            @AttributeOverride(name = "zipcode.zip",
                    column = @Column(name = "ADDR_ZIP"))
    })
    //@Embedded
    protected Address address;
}