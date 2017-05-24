package ru.dmitriy.selectioncommittee.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by diman on 24.05.17.
 */
public class DateFormatUtils {

    public static SimpleDateFormat sdf;

    public static final String DATE_FORMAT = "yyyy.MM.dd";

    static {
        sdf = new SimpleDateFormat(DATE_FORMAT);
    }

    public static long parse(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static int getYear(long time){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        c.setTimeInMillis(time);
        return year - c.get(Calendar.YEAR);
    }

}
