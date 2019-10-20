package com.seyrancom.consulting.app.domain.embeddable;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Pattern;

/**
 * A value object abstraction of an password.
 *
 * @author
 */
@Embeddable
public class Password {

    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Column(name = "password")
    private String value;

    @Transient
    private String password;

    /**
     * Creates a new {@link Password} from the given string source.
     *
     * @param password must not be {@literal null} or empty.
     */
    public Password(String password, String passwordHashCode) {
        assertPassword(password);
        this.password = password;
        this.value = passwordHashCode;
    }

    public static void assertPassword(String password) {
        Assert.isTrue(StringUtils.isNotBlank(password), "Password cannot be empty.");
        Assert.isTrue(isValid(password), "Password must have at least 6 characters, with 1 numeric and 1 uppercase character.");
    }

    protected Password() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;
        Password that = (Password) o;
        return Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**
     * Returns whether the given {@link String} is a valid {@link Password} which means you can safely instantiate the
     * class.
     *
     * @param candidate
     * @return
     */
    public static boolean isValid(String candidate) {
        return candidate == null ? false : StringUtils.isNotBlank(candidate) && PATTERN.matcher(candidate).matches();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}