package cloud.fesh.plugin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * 插件API处理器接口
 * 插件实现此接口来处理自己的API请求
 */
@FunctionalInterface
public interface PluginApiHandler {
    /**
     * 处理API请求
     * @param request HTTP请求
     * @param response HTTP响应
     * @param pathVariables 路径变量
     * @param queryParams 查询参数
     * @param requestBody 请求体（如果有）
     * @return 响应对象，可以是String、Map、ResponseEntity等
     */
    Object handle(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, String> pathVariables,
        Map<String, String> queryParams,
        String requestBody
    ) throws Exception;
}
