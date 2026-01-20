package cloud.fesh.plugin.exception;

/**
 * 插件激活异常
 */
public class PluginActivationException extends RuntimeException {

    public PluginActivationException(String message) {
        super(message);
    }

    public PluginActivationException(String message, Throwable cause) {
        super(message, cause);
    }
}
