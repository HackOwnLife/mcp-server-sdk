package cloud.fesh.msgplatform.utils;

public class AssertUtils {

    private AssertUtils() {}

    public static void isNull(Object object, String msg) {
        if (object == null) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void isNotNull(Object object, String msg) {
        if (object != null) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void isEmpty(String str, String msg) {
        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException(msg);
        }
    }

    public static void isTrue(Boolean expr, String msg, Object... args) {
        if (Boolean.TRUE.equals(expr)) {
            throw new IllegalArgumentException(String.format(msg, args));
        }
    }
}
