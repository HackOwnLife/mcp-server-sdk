package cloud.fesh.plugin;

import cloud.fesh.plugin.exception.PluginLoadException;
import java.util.Collections;
import java.util.Map;
import org.springframework.context.ApplicationContext;

/**
 * 插件上下文
 * 提供插件运行所需的环境信息
 */
public class PluginContext {

    private Map<String, Object> properties = Collections.emptyMap();
    private ApplicationContext applicationContext;
    private PluginConfig pluginConfig;

    /**
     * 获取Spring应用上下文
     * 可用于获取Spring Bean
     *
     * @return Spring应用上下文
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取插件配置
     *
     * @return 插件配置
     */
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    public void setPluginConfig(PluginConfig pluginConfig) {
        this.pluginConfig = pluginConfig;
        // 解析properties JSON字符串为Map
        if (pluginConfig != null && pluginConfig.getProperties() != null) {
            try {
                com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                com.fasterxml.jackson.databind.JsonNode props = mapper.readTree(pluginConfig.getProperties());
                this.properties = mapper.convertValue(props, Map.class);
            } catch (Exception e) {
                throw new PluginLoadException("Invalid plugin properties: " + e.getMessage());
            }
        }
    }

    /**
     * 获取配置属性值
     *
     * @param key 属性键
     * @return 属性值，如果不存在返回NullValue
     */
    public Object getProperty(String key) {
        return properties.getOrDefault(key, NullValue.getInstance());
    }

    /**
     * 设置配置属性值
     *
     * @param key 属性键
     * @param value 属性值
     */
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    /**
     * 设置所有配置属性
     *
     * @param properties 属性Map
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties.putAll(properties);
    }

    /**
     * 获取所有配置属性
     *
     * @return 属性Map
     */
    public Map<String, Object> getProperties() {
        return properties;
    }
}
