package com.seyrancom.consulting.app.domain.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.*;

@Embeddable
public class DatesAndTimes {

    // http://www.developerscrappad.com/228/java/java-ee/ejb3-jpa-dealing-with-date-time-and-timestamp/

    // Date Only:
    java.sql.Date date1;
    //@Temporal(TemporalType.DATE)
    java.sql.Date date2;
    //@Temporal(TemporalType.DATE)
    //java.utils.Calendar date3;

    // Time Only:
    java.sql.Time time1;
    //@Temporal(TemporalType.TIME)
    java.sql.Date time2;
/*    @Temporal(TemporalType.TIME)
    java.utils.Calendar time3;*/

    // Date and Time:
    java.sql.Timestamp dateAndTime1;

/*    @Temporal(TemporalType.TIMESTAMP)
    java.utils.Date dateAndTime2;

    @Temporal(TemporalType.TIMESTAMP)
    java.utils.Calendar dateAndTime3;

    java.utils.Date dateAndTime4; // date and time but not JPA portable
    java.utils.Calendar dateAndTime5;*/

/*    LocalDate localDate;

    LocalTime dateAndTime7;

    LocalDateTime localDateTime;

    OffsetDateTime offsetDateTime;

    Instant instant;*/

/*

    LocalTime localTime = LocalTime.now();
    LocalDate localDate = LocalDate.now();
    LocalDateTime localDateTime = LocalDateTime.now();

    System.out.println(localTime); // 15:06:58.074
    System.out.println(localDate); // 2014-06-30
    System.out.println(localDateTime); // 2014-06-30T15:08:19.809
    */

    /*private static final ThreadLocal<DateFormat> THREAD_SAFE_FORMAT = new ThreadLocal<DateFormat>()
    {
        @Override
        protected DateFormat initialValue() {
            return getFormat();
        }
    };*/
}
