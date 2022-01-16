package com.n11.fourthhomeworkbahadirseven.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.NONE)
public class DateUtil {
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static long getNumberOfDaysWithCurrentDate(Date date) {
        Date currentDate = new Date();
        return TimeUnit.DAYS.convert(currentDate.getTime() - date.getTime(), TimeUnit.MILLISECONDS);
    }
}
