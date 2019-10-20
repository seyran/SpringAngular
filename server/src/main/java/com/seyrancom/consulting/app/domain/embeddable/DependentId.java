package com.seyrancom.consulting.app.domain.embeddable;
// dependent entity uses EmbeddedId for composite key

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DependentId implements Serializable{

    private static final long serialVersionUID = 1L;

    String name;
    long empid;   // corresponds to primary key type of Employee
}