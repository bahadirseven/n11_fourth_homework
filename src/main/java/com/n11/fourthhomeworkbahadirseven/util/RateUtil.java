package com.n11.fourthhomeworkbahadirseven.util;

import java.math.BigDecimal;

public class RateUtil {
    public static BigDecimal calculateRateFromCurrentDate(int year) {
        return year < 2018 ? BigDecimal.valueOf(1.5) : BigDecimal.valueOf(2.0);
    }
}
