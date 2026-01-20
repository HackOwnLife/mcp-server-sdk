package cloud.fesh.plugin.exception;

/**
 * 插件依赖异常
 */
public class PluginDependencyException extends RuntimeException {

    public PluginDependencyException(String message) {
        super(message);
    }

    public PluginDependencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
