package cloud.fesh.msgplatform.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    public static Integer subtractInt(Integer a, Integer b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return a - b;
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return a.subtract(b);
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for multiply");
        AssertUtils.isNull(b, "right can not be null for multiply");
        return a.multiply(b);
    }

    /**
     * 乘法取整
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal multiplyForInt(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return a.multiply(b).setScale(0, RoundingMode.DOWN);
    }

    /**
     * 减到0
     *
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal subtractToZero(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return (ge(a, b)) ? a.subtract(b) : BigDecimal.ZERO;
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return a.add(b);
    }

    public static Integer addWithPossibleBeNull(Integer num, Integer sub) {
        return defaultIfAbsent(num, 0) + defaultIfAbsent(sub, 0);
    }

    public static Integer subtractWithPossibleBeNull(Integer num, Integer sub) {
        return defaultIfAbsent(num, 0) - defaultIfAbsent(sub, 0);
    }

    public static BigDecimal addWithPossibleBeNull(BigDecimal num, BigDecimal add) {
        return defaultIfAbsent(num, BigDecimal.ZERO).add(defaultIfAbsent(add, BigDecimal.ZERO));
    }

    public static BigDecimal subtractWithPossibleBeNull(BigDecimal num, BigDecimal sub) {
        return defaultIfAbsent(num, BigDecimal.ZERO).subtract(defaultIfAbsent(sub, BigDecimal.ZERO));
    }

    public static BigDecimal divideDefaultZero(Number a, Number b) {
        AssertUtils.isNull(a, "left can not be null");
        AssertUtils.isNull(b, "right can not be null");
        BigDecimal a1 = BigDecimal.ZERO;
        BigDecimal b1 = BigDecimal.ZERO;
        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            a1 = (BigDecimal) a;
            b1 = (BigDecimal) b;
        } else if (a instanceof Long && b instanceof Long) {
            a1 = new BigDecimal(a.longValue());
            b1 = new BigDecimal(b.longValue());
        } else if (a instanceof Integer && b instanceof Integer) {
            a1 = new BigDecimal(a.intValue());
            b1 = new BigDecimal(b.intValue());
        } else if (a instanceof Double && b instanceof Double) {
            a1 = BigDecimal.valueOf(a.doubleValue());
            b1 = BigDecimal.valueOf(b.doubleValue());
        }

        return divideDefaultZero(a1, b1, 2);
    }

    public static BigDecimal defaultZero(BigDecimal a) {
        return (a == null) ? BigDecimal.ZERO : a;
    }

    public static BigDecimal divideDefaultZero(BigDecimal a, BigDecimal b, int scale) {
        if (b == null) {
            return BigDecimal.ZERO;
        }
        if (b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return defaultZero(a).divide(defaultZero(b), scale, RoundingMode.HALF_UP);
    }

    public static boolean gt(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return a.compareTo(b) > 0;
    }

    public static boolean ge(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null for add");
        AssertUtils.isNull(b, "right can not be null for add");
        return a.compareTo(b) >= 0;
    }

    public static <T> T defaultIfAbsent(T number, T defaultValue) {
        return (number == null) ? defaultValue : number;
    }

    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null");
        AssertUtils.isNull(b, "right can not be null for add");
        return a.compareTo(b) > 0 ? a : b;
    }

    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        AssertUtils.isNull(a, "left can not be null");
        AssertUtils.isNull(b, "right can not be null");
        return a.compareTo(b) > 0 ? b : a;
    }
}
