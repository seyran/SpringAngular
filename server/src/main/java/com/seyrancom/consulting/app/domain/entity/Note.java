package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "main")
@Entity
//@XmlRootElement(name = "pizza")
@Table(name = "note")
public class Note extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToOne
    private User user;

    @Valid
    @Size(min = 5, message = "Test should be less than 5")
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String text) {
        this.body = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //public DatesAndTimes dates;
}
