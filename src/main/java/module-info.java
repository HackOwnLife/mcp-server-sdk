/**
 * 插件API模块定义
 * 供第三方插件开发者使用
 */
module cloud.fesh.plugin {
    // 导出插件API包
    exports cloud.fesh.plugin;
    exports cloud.fesh.plugin.exception;

    // 以静态方式依赖第三方库，允许在编译时需要，运行时可选
    requires static spring.context;
    requires static spring.webmvc;
    requires static jakarta.servlet;
    requires static com.fasterxml.jackson.databind;

    // 关键配置：开放所有导出包给反射访问
    opens cloud.fesh.plugin;
    opens cloud.fesh.plugin.annotation;
    opens cloud.fesh.plugin.exception;

}
