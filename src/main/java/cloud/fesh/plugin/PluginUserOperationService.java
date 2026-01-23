package cloud.fesh.plugin;


import cloud.fesh.msgplatform.service.dto.AdminUserDTO;

import java.util.Map;

/**
 * 插件用户操作服务接口
 * 用于插件操作用户，提供统一的接口和监控能力
 */
public interface PluginUserOperationService {
    /**
     * 创建或更新用户
     * 根据用户信息（通常来自第三方认证系统）创建新用户或更新现有用户
     *
     * @param pluginName 插件名称，用于审计和监控
     * @param userInfo 用户信息 Map，包含以下字段：
     *                 - sub: 用户唯一标识
     *                 - preferred_username: 用户名
     *                 - name: 显示名称
     *                 - given_name: 名
     *                 - email: 邮箱
     *                 - picture: 头像URL
     *                 - langKey: 语言键
     *                 - email_verified: 邮箱是否已验证
     * @return 用户DTO
     */
    AdminUserDTO createOrUpdateUser(String pluginName, Map<String, Object> userInfo);

    /**
     * 根据登录名查找用户
     *
     * @param pluginName 插件名称，用于审计和监控
     * @param login 登录名
     * @return 用户DTO，如果不存在则返回null
     */
    AdminUserDTO findUserByLogin(String pluginName, String login);

    /**
     * 根据用户ID查找用户
     *
     * @param pluginName 插件名称，用于审计和监控
     * @param userId 用户ID
     * @return 用户DTO，如果不存在则返回null
     */
    AdminUserDTO findUserById(String pluginName, String userId);
}
