package com.seyrancom.consulting.app.domain.entity;

import com.seyrancom.consulting.app.domain.embeddable.Password;
import com.seyrancom.consulting.app.domain.embeddable.EmailAddress;
import com.seyrancom.consulting.app.domain.enums.UserRole;
import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "main")
@NamedQueries({
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u from User u where u.username = :username"),
    @NamedQuery(name = User.FIND_BY_USERNAME + ".count", query = "SELECT count(u) from User u where u.username = :username")
})
public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String FIND_BY_USERNAME = "USER.FIND_BY_USERNAME";

    @OneToMany(mappedBy = "user")
    private Set<Note> notes = new HashSet<>();

    @JsonIgnore
    public Password password;

    @Column(unique = true)
    //@Index(name = "usernameIndex")
    private String username;

    private EmailAddress email;

    //private String role;
    @Type(type="com.seyrancom.consulting.lib.domain.enums.PersistentEnumUserType")
    private UserRole role;

    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("username") String username, @JsonProperty("password") Password password,
                @JsonProperty("role") UserRole role, @JsonProperty("email") EmailAddress email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public EmailAddress getEmail() {
        return email;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equal(notes, user.notes) &&
                Objects.equal(password, user.password) &&
                Objects.equal(username, user.username) &&
                Objects.equal(email, user.email) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), notes, password, username, email, role);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                //.add("notes", notes)
                //.add("password", password)
                .add("username", username)
                .add("email", email)
                .add("userRole", role)
                .add(super.getClass().getSimpleName(), super.toString())
                .toString();
    }
}
