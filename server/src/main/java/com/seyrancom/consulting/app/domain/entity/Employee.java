package com.seyrancom.consulting.app.domain.entity;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "main")
public class Employee extends EmployeeBase {

    private static final long serialVersionUID = 1L;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "emp_salary")
    private BigDecimal salary;

/*    @OneToOne(mappedBy = "employee")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

/*    @ElementCollection
    @CollectionTable(name="Addresses", joinColumns=@JoinColumn(name="user_id"))
    @AttributeOverrides({
            @AttributeOverride(name="street1", column=@Column(name="fld_street"))
    })
    private Set<Address> addresses;*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("salary", salary)
                //.add("address", address)
                .toString();
    }
}