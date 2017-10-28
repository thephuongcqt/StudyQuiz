package com.phuongnt.studyquiz.utils;


import com.phuongnt.studyquiz.AppConst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PhuongNT on 10/28/17.
 */

public class MyDateFormater {
    private static final SimpleDateFormat sdf = new SimpleDateFormat(AppConst.DATE_FORMAT);
    public static String getStringFromDate(Date date){
        return sdf.format(date);
    }
    public static Date getDateFromString(String date) throws ParseException {
        return sdf.parse(date);
    }
}
