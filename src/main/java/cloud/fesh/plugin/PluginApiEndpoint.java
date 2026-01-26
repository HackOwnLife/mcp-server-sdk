package cloud.fesh.plugin;

import java.util.Map;

/**
 * 插件API端点定义
 * 允许插件注册自己的API接口
 */
public class PluginApiEndpoint {
    /**
     * HTTP方法
     */
    public enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH
    }

    private final String path;
    private final HttpMethod method;
    private final PluginApiHandler handler;
    private final boolean requiresAuth;
    private final String[] requiredAuthorities;

    public PluginApiEndpoint(String path, HttpMethod method, PluginApiHandler handler) {
        this(path, method, handler, false, null);
    }

    public PluginApiEndpoint(
        String path,
        HttpMethod method,
        PluginApiHandler handler,
        boolean requiresAuth,
        String[] requiredAuthorities
    ) {
        this.path = path;
        this.method = method;
        this.handler = handler;
        this.requiresAuth = requiresAuth;
        this.requiredAuthorities = requiredAuthorities;
    }

    public String getPath() {
        return path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public PluginApiHandler getHandler() {
        return handler;
    }

    public boolean isRequiresAuth() {
        return requiresAuth;
    }

    public String[] getRequiredAuthorities() {
        return requiredAuthorities;
    }

    /**
     * 检查路径是否匹配
     * 支持简单的路径变量，如 /my-plugin/users/{id}
     */
    public boolean matches(String requestPath) {
        if (path.equals(requestPath)) {
            return true;
        }
        // 简单的路径匹配，支持 {variable} 格式
        // 转义特殊字符，但保留 {variable} 模式
        String pattern = path.replaceAll("\\{[^}]+\\}", "[^/]+");
        // 转义其他正则特殊字符
        pattern = pattern.replaceAll("([.+*?^$()\\[\\]{}|\\\\])", "\\\\$1");
        // 恢复变量匹配模式
        pattern = pattern.replaceAll("\\\\\\[\\\\\\^/\\\\\\]\\\\\\+", "[^/]+");
        return requestPath.matches("^" + pattern + "$");
    }

    /**
     * 提取路径变量
     */
    public Map<String, String> extractPathVariables(String requestPath) {
        Map<String, String> variables = new java.util.HashMap<>();
        String[] patternParts = path.split("/");
        String[] requestParts = requestPath.split("/");

        if (patternParts.length != requestParts.length) {
            return variables;
        }

        for (int i = 0; i < patternParts.length; i++) {
            String patternPart = patternParts[i];
            if (patternPart.startsWith("{") && patternPart.endsWith("}")) {
                String varName = patternPart.substring(1, patternPart.length() - 1);
                variables.put(varName, requestParts[i]);
            }
        }
        return variables;
    }
}
