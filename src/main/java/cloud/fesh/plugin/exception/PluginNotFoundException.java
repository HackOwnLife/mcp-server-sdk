package cloud.fesh.plugin.exception;

/**
 * 插件未找到异常
 */
public class PluginNotFoundException extends RuntimeException {

    public PluginNotFoundException(String message) {
        super(message);
    }
}
