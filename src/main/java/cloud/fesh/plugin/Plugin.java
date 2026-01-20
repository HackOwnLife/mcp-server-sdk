package cloud.fesh.plugin;

/**
 * 插件接口
 * 所有插件必须实现此接口
 */
public interface Plugin {
    /**
     * 初始化插件
     *
     * @param context 插件上下文
     */
    void init(PluginContext context);

    /**
     * 运行插件
     */
    void start();

    /**
     * 停止插件
     */
    void stop();

    /**
     * 获取插件元数据
     *
     * @return 插件元数据
     */
    PluginMetadata getMetadata();
}
