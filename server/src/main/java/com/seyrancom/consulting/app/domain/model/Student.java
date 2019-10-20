package com.seyrancom.consulting.app.domain.model;

import com.seyrancom.consulting.core.domain.entity.AbstractEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Size(min = 3, max = 30)
    private String firstName;

    @Size(min = 3, max = 30)
    private String lastName;

    @NotEmpty
    private String sex;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    @NotNull
    private Date dob;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String section;

    @NotEmpty
    private String country;

    private boolean firstAttempt;

    @NotEmpty
    private List<String> subjects = new ArrayList<String>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isFirstAttempt() {
        return firstAttempt;
    }

    public void setFirstAttempt(boolean firstAttempt) {
        this.firstAttempt = firstAttempt;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName
                + ", sex=" + sex + ", dob=" + dob + ", email=" + email
                + ", section=" + section + ", country=" + country
                + ", firstAttempt=" + firstAttempt + ", subjects=" + subjects
                + "]";
    }

}