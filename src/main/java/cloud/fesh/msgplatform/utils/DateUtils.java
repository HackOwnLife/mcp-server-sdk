package cloud.fesh.msgplatform.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static int ONE_DAY_MILLISECONDS = 24 * 60 * 60 * 1000;
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static String[] parsePatterns = {
        "yyyy-MM-dd",
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyy-MM",
        "yyyy/MM/dd",
        "yyyy/MM/dd HH:mm:ss",
        "yyyy/MM/dd HH:mm",
        "yyyy/MM",
        "yyyy.MM.dd",
        "yyyy.MM.dd HH:mm:ss",
        "yyyy.MM.dd HH:mm",
        "yyyy.MM",
    };
    public static String YYYY = "yyyy";
    public static String YYYY_MM = "yyyy-MM";
    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    public static String getEarliestDateStr() {
        return "1970-01-01";
    }

    public static Date getEarliestDate() {
        return parseDate("1970-01-01");
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getDateTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取指定日期的第一秒(最早时间)
     *
     * @param date
     * @return
     */
    public static Date getDateBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDateTime parseTimestamp(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }

    public static Date parseDateOrDefault(Object dateTime, Date defaultDate) {
        if (dateTime == null) {
            return defaultDate;
        }
        try {
            return parseDate(dateTime.toString(), parsePatterns);
        } catch (ParseException e) {
            return defaultDate;
        }
    }

    public static LocalDateTime parseLocalDateTime(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        return LocalDateTime.parse(dateTime);
    }

    public static LocalDate parseLocalDate(String dateTime) {
        if (dateTime == null) {
            return null;
        }
        return LocalDate.parse(dateTime);
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = (diff % nd) / nh;
        // 计算差多少分钟
        long min = (diff % nd % nh) / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor) {
        if (temporalAccessor == null) {
            return null;
        }
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor) {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, "yyyy/MM/dd");
    }

    public static String formatShortDate(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, "yy/MM/dd");
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, pattern);
    }

    public static String formatDate(LocalDate localDate) {
        return DateFormatUtils.format(convertToDate(localDate), "yyyy-MM-dd");
    }

    public static String formatDate(LocalDateTime localDateTime) {
        return DateFormatUtils.format(convertToDate(localDateTime), "yyyy-MM-dd");
    }

    public static Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String format2DateTime(Long dateTimeStamp) {
        return DateFormatUtils.format(new Date(dateTimeStamp), YYYY_MM_DD_HH_MM_SS);
    }

    public static List<Date> getBetweenDays(Date startDate, Date endDate) {
        AssertUtils.isNull(startDate, "startDate can not be null");
        AssertUtils.isNull(endDate, "endDate can not be null");
        AssertUtils.isTrue(endDate.before(startDate), "endDate can not be before startDate");
        // 创建 Calendar 对象并设置为开始日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        List<Date> result = new ArrayList<>();
        long endDateStamp = endDate.getTime();
        while (calendar.getTime().getTime() < endDateStamp) {
            Date current = calendar.getTime();
            result.add(current);
            calendar.add(Calendar.DAY_OF_MONTH, 1); // 增加一天
        }
        return result;
    }

    public static int getBetweenDaySize(Date startDate, Date endDate) {
        AssertUtils.isNull(startDate, "startDate can not be null");
        AssertUtils.isNull(endDate, "endDate can not be null");
        if (startDate.after(endDate)) {
            Date tmp = startDate;
            startDate = endDate;
            endDate = tmp;
        }

        return (int) ((endDate.getTime() - startDate.getTime()) / ONE_DAY_MILLISECONDS);
    }

    public static Date subDays(Date date, Integer subDay) {
        if (date == null) {
            return null;
        }
        return addDays(date, -subDay);
    }

    public static Date defaultIfAbsent(Date date) {
        if (date == null) {
            return new Date();
        } else {
            return date;
        }
    }

    public static Date getMonthStartDate(Date date) {
        if (date == null) {
            return null;
        }
        YearMonth yearMonth = YearMonth.from(date.toInstant().atZone(ZoneId.systemDefault()));
        LocalDate startOfMonth = yearMonth.atDay(1);
        return convertToDate(startOfMonth);
    }

    public static Date getMonthEndDate(Date date) {
        if (date != null) {
            YearMonth yearMonth = YearMonth.from(date.toInstant().atZone(ZoneId.systemDefault()));
            LocalDate endOfMonth = yearMonth.atEndOfMonth();
            return convertToDate(endOfMonth);
        }
        return null;
    }

    /**
     * 将时间字符串转换为秒数
     *
     * @param timeStr 时：分：秒
     * @return
     */
    public static Integer convertToSeconds(String timeStr) {
        String[] timeParts = timeStr.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int secs = Integer.parseInt(timeParts[2]);

        return hours * 3600 + minutes * 60 + secs;
    }

    public static Date getCurrentYearStartDate() {
        return getCurrentYearStartDate(getNowDate());
    }

    public static Date getCurrentYearStartDate(Date currentDate) {
        Year year = Year.from(currentDate.toInstant().atZone(ZoneId.systemDefault()));
        YearMonth yearMonth = year.atMonth(1);
        return convertToDate(yearMonth.atDay(1));
    }
}
