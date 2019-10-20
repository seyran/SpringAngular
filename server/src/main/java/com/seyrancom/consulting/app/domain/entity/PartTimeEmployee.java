package com.seyrancom.consulting.app.domain.entity;

/*@Entity
@AttributeOverride(name="address", column=@Column(name="ADDR"))*/
public class PartTimeEmployee extends Employee {

    private static final long serialVersionUID = 1L;

    // address field mapping overridden to ADDR
    protected Float wage;

    public Float getWage() {
        return wage;
    }

    public void setWage(Float wage) {
        this.wage = wage;
    }
}