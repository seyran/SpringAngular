package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.app.domain.embeddable.DependentId;
import com.seyrancom.consulting.core.domain.entity.AbstractEntity;

import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

//@Entity
public class Dependent extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    DependentId id;

    @MapsId("empid")  //  maps the empid attribute of embedded id
    @ManyToOne
    Employee emp;
}