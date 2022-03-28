package id.co.nds.shop.generators;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateGenerator {
    private static final SimpleDateFormat dateCodeFormatter = new SimpleDateFormat("yyyyMMdd");

    public static Date generateDate() {
        return new Date(System.currentTimeMillis());
    }

    public static String generateTodayCode() {
        return dateCodeFormatter.format(generateDate());
    }

    public static Timestamp generateTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
