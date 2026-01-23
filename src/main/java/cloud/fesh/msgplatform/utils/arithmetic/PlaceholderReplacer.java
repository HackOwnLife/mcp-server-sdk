package cloud.fesh.msgplatform.utils.arithmetic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderReplacer {

    public static String replacePlaceholders(String exp, String[] placeholders) {
        // 验证参数数量是否匹配
        if (exp == null || placeholders == null) {
            throw new IllegalArgumentException("Invalid arg");
        }

        // 构建正则表达式，匹配所有的占位符 {}
        String regex = "\\{([^}]+)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(exp);

        // 用于构建替换后的字符串
        StringBuilder sb = new StringBuilder();
        Map<String, String> result = new HashMap<>();
        while (matcher.find()) {
            // 提取占位符名称
            String placeholderName = matcher.group(1);
            result.put(placeholderName, placeholders[Integer.parseInt(placeholderName) - 1]);
            //System.out.println(placeholderIndex + "::" +placeholderName);
        }

        //替换占位符
        for (Map.Entry<String, String> o : result.entrySet()) {
            exp = exp.replaceAll("\\{" + o.getKey() + "\\}", o.getValue());
        }

        //去除空格
        exp = exp.replaceAll(" ", "");

        return exp;
    }

//    public static void main(String[] args) {
//        String exp = "{1} + ({2} + {2})";
//        String[] placeholders = { "55", "30" };
//        String result = replacePlaceholders(exp, placeholders);
//        System.out.println(result);
//    }
}
