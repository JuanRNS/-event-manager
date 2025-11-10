package com.example.eventmanagerbackend.infrastructure.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DateUtils {

    private DateUtils() {}

    public static boolean initialWeek(LocalDateTime todayDate){
        LocalDate todayDateParty = todayDate.toLocalDate();
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate lastMonday = monday.minusWeeks(1);
        LocalDate lastSunday = lastMonday.plusDays(6);

        return !todayDateParty.isBefore(lastMonday) && !todayDateParty.isAfter(lastSunday);
    }
}
