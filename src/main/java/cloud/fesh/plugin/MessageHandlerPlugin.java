package cloud.fesh.plugin;

import cloud.fesh.plugin.enums.AuthProviderTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * 消息处理器插件接口
 * 用于处理来自第三方认证提供者的消息请求
 * 支持多种认证提供者同时运行
 */
public interface MessageHandlerPlugin extends Plugin {
    /**
     * 获取认证提供者类型
     *
     * @return 提供者类型，如 WECOM, DINGTALK 等
     */
    AuthProviderTypeEnum getProviderType();

    /**
     * 获取该提供者支持的消息处理路径前缀
     * 例如: "/wecom", "/dingtalk" 等
     *
     * @return 路径前缀
     */
    String getPathPrefix();

    /**
     * 处理GET请求
     *
     * @param request       HTTP请求
     * @param response      HTTP响应
     * @param pathVariables 路径变量
     * @param queryParams   查询参数
     * @return 响应内容
     */
    default Object handleGet(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, String> pathVariables,
        Map<String, String> queryParams
    ) {
        throw new UnsupportedOperationException("该消息处理器不支持GET请求");
    }

    /**
     * 处理POST请求
     *
     * @param request       HTTP请求
     * @param response      HTTP响应
     * @param pathVariables 路径变量
     * @param queryParams   查询参数
     * @param requestBody   请求体
     * @return 响应内容
     */
    default Object handlePost(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, String> pathVariables,
        Map<String, String> queryParams,
        String requestBody
    ) {
        throw new UnsupportedOperationException("该消息处理器不支持POST请求");
    }

    /**
     * 处理PUT请求
     *
     * @param request       HTTP请求
     * @param response      HTTP响应
     * @param pathVariables 路径变量
     * @param queryParams   查询参数
     * @param requestBody   请求体
     * @return 响应内容
     */
    default Object handlePut(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, String> pathVariables,
        Map<String, String> queryParams,
        String requestBody
    ) {
        throw new UnsupportedOperationException("该消息处理器不支持PUT请求");
    }

    /**
     * 处理DELETE请求
     *
     * @param request       HTTP请求
     * @param response      HTTP响应
     * @param pathVariables 路径变量
     * @param queryParams   查询参数
     * @return 响应内容
     */
    default Object handleDelete(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, String> pathVariables,
        Map<String, String> queryParams
    ) {
        throw new UnsupportedOperationException("该消息处理器不支持DELETE请求");
    }

    /**
     * 验证请求是否应该由该插件处理
     *
     * @param request HTTP请求
     * @return 是否应该处理
     */
    default boolean canHandle(HttpServletRequest request) {
        String path = request.getRequestURI();
        String prefix = getPathPrefix();
        return path != null && prefix != null && path.startsWith(prefix);
    }
}
