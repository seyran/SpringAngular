package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import javax.persistence.Column;

/*@Entity
@Table(name = "product")*/
public class Product extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    private String name;

    public Product() {
    }

    public Product(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product [id=" + getId() + ", name=" + name + "]";
    }

    /*@Access(AccessType.PROPERTY)
    @Column(name = "ITEM_NAME")
    protected String name;*/

    /*@org.hibernate.annotations.Formula(
            "substr(DESCRIPTION, 1, 12) || '...'"
    )
    protected String shortDescription;
    @org.hibernate.annotations.Formula(
            "(select avg(b.AMOUNT) from BID b where b.ITEM_ID = ID)"
    )
    protected BigDecimal averageBidAmount;*/

    @Column(name = "IMPERIALWEIGHT")
    @org.hibernate.annotations.ColumnTransformer(
            read = "IMPERIALWEIGHT / 2.20462",
            write = "? * 2.20462"
    )
    protected double metricWeight;

    /*@Column(name = "status_id", nullable = false, unique = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;*/
}