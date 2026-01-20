package cloud.fesh.plugin.exception;

/**
 * 插件加载异常
 */
public class PluginLoadException extends RuntimeException {

    public PluginLoadException(String message) {
        super(message);
    }

    public PluginLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
