package nl.movie.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class MoviesDateUtil {

    private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String HH_MM_SS = "HH:mm:ss";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";


    private MoviesDateUtil() {
    }

    public static String extractDate(final String dateTimeString) {
        try {
            final Date date = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS).parse(dateTimeString);
            return new SimpleDateFormat(YYYY_MM_DD).format(date);
        } catch (final Exception e) {
            return "";
        }
    }

    public static String extractTime(final String dateTimeString) {
        try {
            final Date date = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS).parse(dateTimeString);
            return new SimpleDateFormat(HH_MM_SS).format(date);
        } catch (final Exception e) {
            return "";
        }
    }

}