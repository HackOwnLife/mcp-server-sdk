/**
 * 插件API模块定义
 * 供第三方插件开发者使用
 */
module cloud.fesh.msgplatform.plugin.api {
    // 导出插件API包
    exports cloud.fesh.plugin;
    exports cloud.fesh.plugin.exception;

    // 需要Spring Context
    requires spring.context;
    
    // 需要Spring Web MVC
    requires spring.webmvc;
    
    // 需要Jakarta Servlet API
    requires jakarta.servlet;
    
    // 需要Jackson
    requires com.fasterxml.jackson.databind;
}
