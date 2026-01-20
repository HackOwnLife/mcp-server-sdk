package cloud.fesh.plugin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 * 请求处理插件接口
 * 用于处理HTTP请求的插件应实现此接口
 */
public interface RequestHandlerPlugin extends Plugin {
    /**
     * 是否支持该请求
     *
     * @param request HTTP请求
     * @return 是否支持处理该请求
     */
    boolean support(HttpServletRequest request);

    /**
     * 请求处理前
     *
     * @param request HTTP请求
     * @param response HTTP响应
     */
    void preHandle(HttpServletRequest request, HttpServletResponse response);

    /**
     * 请求处理后
     *
     * @param request HTTP请求
     * @param response HTTP响应
     * @param handler 处理器
     * @param modelAndView 模型和视图
     */
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView);
}
