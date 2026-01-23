package cloud.fesh.plugin;

import java.util.Map;

/**
 * 认证提供者插件接口
 * 用于支持不同的第三方认证方式（如企业微信、钉钉等）
 */
public interface AuthProviderPlugin extends Plugin {
    /**
     * 获取认证提供者类型
     * @return 提供者类型，如 "WECOM", "DINGTALK" 等
     */
    String getProviderType();

    /**
     * 通过授权码获取用户信息
     * @param code 授权码
     * @return 用户信息 Map
     */
    Map<String, Object> getUserInfoByCode(String code);

    /**
     * 获取访问令牌
     * @return 访问令牌
     */
    String getAccessToken();

    /**
     * 验证消息签名（用于企业微信等需要验证消息的场景）
     * @param msg 消息体签名
     * @param timestamp 时间戳
     * @param nonce 随机数字串
     * @param echoStr 随机加密字符串
     * @return 验证结果
     */
    default String receiveData(String msg, String timestamp, String nonce, String echoStr) {
        throw new UnsupportedOperationException("该认证提供者不支持消息验证");
    }

    /**
     * 接收并处理消息（用于企业微信等需要接收消息的场景）
     * @param msg 消息体签名
     * @param timestamp 时间戳
     * @param nonce 随机数字串
     * @param msgBody 消息体
     * @return 处理后的响应
     */
    default String receiveComm(String msg, String timestamp, String nonce, String msgBody) {
        throw new UnsupportedOperationException("该认证提供者不支持消息接收");
    }
}
