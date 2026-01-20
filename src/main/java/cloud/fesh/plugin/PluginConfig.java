package cloud.fesh.plugin;

import java.io.Serializable;

/**
 * 插件配置
 * 简化版本，不包含JPA注解
 */
public class PluginConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private boolean enabled;
    private String properties;
    private Integer priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
