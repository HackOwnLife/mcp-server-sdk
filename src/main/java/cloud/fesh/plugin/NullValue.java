package cloud.fesh.plugin;

/**
 * 空值占位符
 * 用于表示配置属性不存在
 */
public class NullValue {

    private static final NullValue NULL_VAL = new NullValue();

    public static NullValue getInstance() {
        return NULL_VAL;
    }

    private NullValue() {}
}
