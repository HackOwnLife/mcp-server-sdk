/**
 * 对企业微信发送给企业后台的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package cloud.fesh.msgplatform.utils;

import cloud.fesh.msgplatform.exception.AesException;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

/**
 * SHA1 class
 * <p>
 * 计算消息签名接口.
 */
public class SHAEncrypt {

    /**
     * 用SHA1算法生成安全签名
     *
     * @param token     票据
     * @param timestamp 时间戳
     * @param nonce     随机字符串
     * @param encrypt   密文
     * @return 安全签名
     * @throws AesException
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws AesException {
        String[] array = new String[] { token, timestamp, nonce, encrypt };
        return getSHA(Arrays.stream(array).toList(), "SHA-1");
    }

    public static String getSHA1(List<String> contentList) throws AesException {
        return getSHA(contentList, "SHA-1");
    }

    public static String getSHA256(List<String> contentList) throws AesException {
        return getSHA(contentList, "SHA-256");
    }

    public static String getSHA(List<String> contentList, String digestMethod) throws AesException {
        try {
            contentList.sort(String::compareTo);
            String contentStr = String.join("", contentList);
            MessageDigest md = MessageDigest.getInstance(digestMethod);
            md.update(contentStr.getBytes());

            byte[] digest = md.digest();
            StringBuilder hexStr = new StringBuilder();
            String shaHex;
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (Exception e) {
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}
