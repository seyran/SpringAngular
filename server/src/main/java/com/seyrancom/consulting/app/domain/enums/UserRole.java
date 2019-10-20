package com.seyrancom.consulting.app.domain.enums;

import com.seyrancom.consulting.config.security.SecurityConfig;
import com.seyrancom.consulting.core.domain.enums.PersistentEnum;
import com.google.common.base.MoreObjects;

public enum UserRole implements PersistentEnum {

    ANONYMOUS(SecurityConfig.UserRole.ANONYMOUS.getId(), "Anonymous"),
    USER(SecurityConfig.UserRole.USER.getId(), "User"),
    ADMIN(SecurityConfig.UserRole.ADMIN.getId(), "Admin");

    public int id;
    public int getId() {
        return id;
    }

    public String label;
    public String getLabel() {
        return label;
    }

    UserRole(int id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("label", label)
                .toString();
    }
}