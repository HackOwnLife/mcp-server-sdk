package cloud.fesh.plugin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface RequestHandlerPlugin extends Plugin {
    /**
     * 是否支持该请求
     *
     * @param request
     * @return
     */
    boolean support(HttpServletRequest request);

    /**
     * 请求处理前
     *
     * @param request
     * @param response
     */
    void preHandle(HttpServletRequest request, HttpServletResponse response);

    /**
     * 请求处理后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView);
}
