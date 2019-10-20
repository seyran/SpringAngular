package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.app.domain.embeddable.PropertyInfo;
import com.seyrancom.consulting.core.domain.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Map;

//@Entity
public class PropertyRecord extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @AttributeOverrides({
            @AttributeOverride(name = "key.street",
                    column = @Column(name = "STREET_NAME")),
            @AttributeOverride(name = "value.size",
                    column = @Column(name = "SQUARE_FEET")),
            @AttributeOverride(name = "value.tax",
                    column = @Column(name = "ASSESSMENT"))
    })
    @ElementCollection
    Map<Address, PropertyInfo> parcels;
}