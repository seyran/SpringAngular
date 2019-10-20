package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.app.domain.embeddable.Zipcode;
import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
/*@NamedQuery(name = "Address.findByAddrLine1CityStateZip", query = "select o from Address o where o.addrLine1 = :addrLine1" +
        " and o.city = :city and o.state = :state and o.zip = :zip")*/
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE, region="main")
public class Address extends AbstractEntity implements Comparable<Address> {

    private static final long serialVersionUID = 1L;

    public enum AddressType {
        RENTAL, HOMEOWNER, BUSINESS
    }

    private String addrLine1;
    private String addrLine2;
    private String city;
    private String state;
    @Embedded private Zipcode zip;

    @Enumerated(EnumType.STRING)
    private final AddressType type;
/*
    @OneToOne
    @PrimaryKeyJoinColumn
    private Employee employee;*/

    // Default constructor used by Hibernate
    public Address() {
        this.addrLine1 = null;
        this.addrLine2 = null;
        this.city = null;
        this.state = null;
        this.zip = null;
        this.type = null;
    }

    @JsonCreator
    public Address(@JsonProperty("addrLine1") String addrLine1, @JsonProperty("addrLine2") String addrLine2,
                   @JsonProperty("city") String city, @JsonProperty("state") String state,
                   @JsonProperty("zip") Zipcode zip, @JsonProperty("enums") AddressType type) {
        this.addrLine1 = addrLine1;
        this.addrLine2 = addrLine2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.type = type;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public String getAddrLine2() {
        return addrLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Zipcode getZip() {
        return zip;
    }

    public AddressType getType() {
        return type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("addrLine1", addrLine1).add("city", city).add("state", state)
                .add("zip", zip).toString();
    }

    @Override
    public int compareTo(Address that) {
        return ComparisonChain.start().compare(this.addrLine1, that.addrLine1).compare(this.city, that.city)
                .compare(this.city, that.city).compare(this.zip, that.zip, Ordering.natural().nullsLast()).result();

    }

    @Override
    public boolean equals(Object obj) {
        Address that = (Address) obj;
        return Objects.equal(this.addrLine1, that.addrLine1) && Objects.equal(this.city, that.city)
                && Objects.equal(this.state, that.state) && Objects.equal(this.zip, that.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addrLine1, city, state, zip);
    }

}