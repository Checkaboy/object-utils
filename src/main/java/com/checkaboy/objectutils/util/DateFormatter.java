package com.checkaboy.objectutils.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public static final String DEFAULT_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";

    /**
     * Функция форматирования даты
     *
     * @param date объект содержащий дату
     * @return форматированную строку даты в виде:
     * dd-MM-yyyy HH:mm:ss
     */
    public static String dateFormatted(Object date) {
        return dateFormatted(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * Функция форматирования даты
     *
     * @param date    объект содержащий дату
     * @param pattern формат вывода даты
     * @return форматированную строку даты
     * @see SimpleDateFormat
     */
    public static String dateFormatted(Object date, String pattern) {
        if (date instanceof Date) {
            Format formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } else if (date instanceof LocalDateTime) {
            return dateFormatted(convertToDateViaInstant((LocalDateTime) date), pattern);
        } else if (date instanceof LocalDate) {
            return dateFormatted(convertToDateViaInstant((LocalDate) date), pattern);
        }

        return "";
    }

    /**
     * @param date    строка с данными о дате
     * @param pattern патерн для чтения
     * @return дата
     */
    public static Date parsDate(String date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return parseDate(formatter, date);
    }

    /**
     * @param date    строка с данными о дате
     * @param pattern патерн для чтения
     * @param locale  дополнение локализации к паттерну
     * @return дата
     */
    public static Date parsDate(String date, String pattern, Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
        return parseDate(formatter, date);
    }

    /**
     * @param formatter формат даты в строке
     * @param date      строка с датой
     * @return объект даты
     */
    private static Date parseDate(SimpleDateFormat formatter, String date) {
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        if (dateToConvert == null) return null;

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaMillisecond(Date dateToConvert) {
        if (dateToConvert == null) return null;

        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        if (dateToConvert == null) return null;

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate convertToLocalDateViaMillisecond(Date dateToConvert) {
        if (dateToConvert == null) return null;

        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        if (dateToConvert == null) return null;

        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        if (dateToConvert == null) return null;

        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

}
