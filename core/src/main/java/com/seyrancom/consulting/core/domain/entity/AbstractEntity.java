package com.seyrancom.consulting.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * This is a common class for all entities. It provides an equals and hashcode that will always work correctly in all
 * circumstances. This avoids frequent errors related to the implementation of those same methods.
 */
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
// Define a sequence - might also be in another class:
@Table(indexes = {@Index(name = "version", columnList = "version")})
public abstract class AbstractEntity implements Serializable, ModelEntity {
    private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            //, generator = "SEQ_GEN"
    )
/*    @SequenceGenerator(
            name="SEQ_GEN",
            sequenceName="my_sequence",
            initialValue=1, allocationSize=50, optimizer="pooled"
    )*/
/*    @GenericGenerator(
            name =  "SEQ_GEN",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator" ,
            parameters = {
                    @Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @Parameter(name = "sequence_per_entity_suffix", value = "_id_seq"),
                    @Parameter(name = "initial_value", value = "100"),
                    @Parameter(name = "increment_size", value = "1")
            })*/
    @Column(name = "ID", updatable = false, unique = true, nullable = false)
    private Long id;

    public AbstractEntity() {
    }

    public AbstractEntity(long id) {
        setId(id);
    }

    @JsonView
    //@JsonIgnore
    //@Transient
    @Version
    private long version;
    private long getVersion() {
        return version;
    }

    private void setVersion(long version) {
        this.version = version;
    }

    private Date getCreated() {
        return created;
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    private Date getUpdated() {
        return updated;
    }

    private void setUpdated(Date updated) {
        this.updated = updated;
    }

    //@Temporal(TemporalType.TIMESTAMP)
    //@Generated(GenerationTime.INSERT)
    @CreationTimestamp
    @Column(name = "created", insertable=true, updatable=false)
    private Date created;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Generated(GenerationTime.INSERT)
    @UpdateTimestamp
    @Column(name = "updated", insertable=true, updatable=true)
    private Date updated;

/*    @JsonIgnore
    @Transient
    private UUID uuid;

    @Column(name = "UUID")
    private String uuidStr;*/

  /*  @PrePersist
    protected void prePersist() {
        updated = created = new Date();
        syncUuidString();
    }

    @PreUpdate
    protected void preUpdate() {
        updated = new Date();
    }

    protected void syncUuidString() {
        if (null == uuidStr) {
            // initial method call fills the uuid
            getUuid();
        }
    }

    public UUID getUuid() {
        if (uuidStr == null) {
            if (uuid == null) {
                uuid = UUID.randomUUID();
            }
            uuidStr = uuid.toString();
        }
        if (uuid == null && uuidStr != null) {
            uuid = UUID.fromString(uuidStr);
        }
        return uuid;
    }

    public String getUuidStr() {
        return uuidStr;
    }*/

    @Override
    public Long getId() {
        return id;
    }

    /*
    *
    *   This method is mean for testing purposes only (create mock data), as we should not set Ids manually,
    *   Hibernate will do it automatically
    *
    **/
    @Override
    public void setId(Long id) {
        this.id = id;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        //return getId() != null ? getId().hashCode() : 0;
        return getUuid() != null ? getUuid().hashCode() : 0;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return version == that.version &&
                Objects.equal(id, that.id) &&
                Objects.equal(created, that.created) &&
                Objects.equal(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, version, created, updated);
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id &&
                version == that.version &&
                Objects.equal(created, that.created) &&
                Objects.equal(updated, that.updated) &&
                Objects.equal(uuid, that.uuid) &&
                Objects.equal(uuidStr, that.uuidStr);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, version, created, updated, uuid, uuidStr);
    }*/

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("version", version)
                .add("created", created)
                .add("updated", updated)
                //.add("uuid", uuid)
                //.add("uuidStr", uuidStr)
                .toString();
    }

    /*private static final long serialVersionUID = 1L;
    @Embedded
    private List<Link> links = new LinkedList<Link>();

    public void addLink(Link link) {
        this.links.add(link);
    }

    public List<Link> getLinks() {
        if (links == null) {
            links = new LinkedList<Link>();
        }
        return links;
    }*/
}