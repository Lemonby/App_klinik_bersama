package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String date_pettern = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(date_pettern);

    // Convert String ke java.util.Date
    public static Date parse(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    // Convert java.util.Date ke String
    public static String Format(Date tglLahir) {
        return tglLahir != null ? dateFormat.format(tglLahir) : null;
    }

    // Convert java.util.Date ke java.sql.Date
    public static java.sql.Date toSqlDate(Date date) {
        return date != null ? new java.sql.Date(date.getTime()) : null;
    }

    // Convert java.sql.Date ke java.util.Date
    public static Date fromSqlDate(java.sql.Date sqlDate) {
        return sqlDate != null ? new Date(sqlDate.getTime()) : null;
    }
}
