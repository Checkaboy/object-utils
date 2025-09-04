package com.checkaboy.objectutils.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {

    public static boolean isBetween(Date start, Date end, Date comparable) {
        if (comparable == null) return false;
        return comparable.getTime() >= start.getTime()
                && comparable.getTime() <= end.getTime();
    }

    public static boolean isBetween(LocalDateTime start, LocalDateTime end, LocalDateTime comparable) {
        if (comparable == null) return false;
        return comparable.isAfter(start) && comparable.isBefore(end);
    }

    public static boolean isBetween(LocalDate start, LocalDate end, LocalDate comparable) {
        if (comparable == null) return false;
        return comparable.isAfter(start) && comparable.isBefore(end);
    }

}
