package com.metaphorce.taskFlow.util;

import java.time.LocalDate;
import java.time.Period;

public class TaskUtil {

    public static int getElapsedTimeBetweenDates(String startDate, String endDate){
        Period elapsedTime = Period.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return elapsedTime.getDays() * 24;
    }
}
