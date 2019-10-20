package com.seyrancom.consulting.app.domain.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class Zipcode implements Comparable<Zipcode> {
    protected String zip;
    protected String plusFour;

    @Override
    public int compareTo(Zipcode that) {
        if (this.zip.compareTo(that.zip) < 0) {
            return -1;
        } else if (this.zip.compareTo(that.zip) > 0) {
            return 1;
        }

        if (this.plusFour.compareTo(that.plusFour) < 0) {
            return -1;
        } else if (this.plusFour.compareTo(that.plusFour) > 0) {
            return 1;
        }
        return 0;
    }
}