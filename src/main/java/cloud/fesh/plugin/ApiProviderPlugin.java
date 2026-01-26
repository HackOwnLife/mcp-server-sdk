package cloud.fesh.plugin;

import java.util.List;

/**
 * API提供者插件接口
 * 允许插件注册自己的API端点
 */
public interface ApiProviderPlugin extends Plugin {
    /**
     * 获取插件注册的API端点列表
     * 插件在初始化时应该注册自己的API端点
     * @return API端点列表
     */
    List<PluginApiEndpoint> getApiEndpoints();

    /**
     * 获取插件API的基础路径前缀
     * 例如: "/api/plugin/my-plugin"
     * @return 基础路径前缀
     */
    String getApiBasePath();
}
