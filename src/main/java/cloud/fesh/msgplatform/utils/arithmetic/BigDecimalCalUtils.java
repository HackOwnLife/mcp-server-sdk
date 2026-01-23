package cloud.fesh.msgplatform.utils.arithmetic;

import java.math.BigDecimal;

public class BigDecimalCalUtils {

    public static BigDecimal cal(String exp, BigDecimal... ops) {
        String[] items = new String[ops.length];
        for (int i = 0; i < ops.length; i++) {
            if (ops[i] == null) {
                items[i] = "0";
            } else {
                items[i] = String.valueOf(ops[i].doubleValue());
            }
        }
        String newExp = PlaceholderReplacer.replacePlaceholders(exp, items);
        String result = new Arithmetic(newExp).calculate();
        return new BigDecimal(result);
    }

//    public static void main(String[] args) {
//        String exp = "{1} + ({2} + {2})";
//        BigDecimal result = cal(exp, new BigDecimal(10.22), new BigDecimal(22));
//        System.out.println(result);
//    }
}
