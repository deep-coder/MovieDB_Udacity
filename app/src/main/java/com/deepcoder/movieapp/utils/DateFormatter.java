package com.deepcoder.movieapp.utils;

/**
 * Created by jdeepak on 12/26/2015.
 */
public class DateFormatter {

    public static String formatDate(String date) {
        String[] dateArray = date.split("-");
        String month="";
        switch (dateArray[1]) {
            case "01":
                month=Constants.MONTH_JANUARY;
                break;
            case "02":
                month=Constants.MONTH_FEBRUARY;
                break;
            case "03":
                month=Constants.MONTH_MARCH;
                break;
            case "04":
                month=Constants.MONTH_APRIL;
                break;
            case "05":
                month=Constants.MONTH_MAY;
                break;
            case "06":
                month=Constants.MONTH_JUNE;
                break;
            case "07":
                month=Constants.MONTH_JULY;
                break;
            case "08":
                month=Constants.MONTH_AUGUST;
                break;
            case "09":
                month=Constants.MONTH_SEPTEMBER;
                break;

            case "10":
                month=Constants.MONTH_OCTOBER;
                break;
            case "11":
                month=Constants.MONTH_NOVEMBER;
                break;
            case "12":
                month=Constants.MONTH_DECEMBER;
                break;


        }
        return month+", "+dateArray[0];
    }
}
