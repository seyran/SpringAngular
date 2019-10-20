package com.seyrancom.consulting.app.domain.embeddable;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by admin on 6/15/2016.
 */
@Embeddable
public class PropertyInfo {
    Integer parcelNumber;
    Integer size;
    BigDecimal tax;
}