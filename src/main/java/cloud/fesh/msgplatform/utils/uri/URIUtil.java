package cloud.fesh.msgplatform.utils.uri;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class URIUtil {

    public static String encode(String uri) {
        if (uri == null) {
            return null;
        }
        try {
            return URLEncoder.encode(uri, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return uri;
        }
    }

    public static String decode(String uri) {
        if (uri == null) {
            return null;
        }
        try {
            return URLDecoder.decode(uri, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return uri;
        }
    }

    public static String getCurrentURL(String url, String paramName, String paramValue) {
        if (url == null) {
            return null;
        }
        int pos = url.indexOf("?");
        if (pos == -1) {
            return url + "?" + paramName + "=" + paramValue;
        } else {
            return url.substring(0, pos + 1) + paramName + "=" + paramValue + "&" + url.substring(pos + 1);
        }
    }

    /**
     * 对字符串进行URL编码
     * @param str 待编码的字符串
     * @return URL编码后的字符串
     */
    public static String urlEncode(String str) {
        if (str == null) {
            return null;
        }

        try {
            // 使用UTF-8编码进行URL编码，确保特殊字符被正确转义
            return URLEncoder.encode(str, StandardCharsets.UTF_8)
                .replace("+", "%20") // 将+替换为空格的编码，符合严格URL规范
                .replace("*", "%2A")
                .replace("%7E", "~"); // 将波浪号的编码还原，符合RFC 3986
        } catch (Exception e) {
            throw new RuntimeException("URL编码失败", e);
        }
    }

    public static URL toURL(String url) {
        if (url == null) {
            return null;
        }
        URI uri;
        try {
            // 对路径部分进行编码
            String encodedUrl = encodeUrl(url);
            uri = new URI(encodedUrl);
            return uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String encodeUrl(String url) {
        if (url == null) {
            return null;
        }

        try {
            // 使用URI类正确解析URL，避免手动分割可能带来的问题
            URI uri = new URI(url);

            String scheme = uri.getScheme();
            String authority = uri.getAuthority();
            String path = uri.getPath();
            String query = uri.getQuery();
            String fragment = uri.getFragment();

            StringBuilder result = new StringBuilder();

            // 重建URL各部分
            if (scheme != null) {
                result.append(scheme).append("://");
            }

            if (authority != null) {
                result.append(authority);
            }

            if (path != null) {
                // 对路径部分进行编码，只编码需要编码的字符
                String encodedPath = path.replaceAll(" ", "%20").replaceAll("\\(", "%28").replaceAll("\\)", "%29");
                result.append(encodedPath);
            }

            if (query != null) {
                // 查询参数使用URLEncoder进行编码
                result.append("?").append(URLEncoder.encode(query, StandardCharsets.UTF_8));
            }

            if (fragment != null) {
                // 片段部分也进行适当编码
                String encodedFragment = fragment.replaceAll(" ", "%20").replaceAll("\\(", "%28").replaceAll("\\)", "%29");
                result.append("#").append(encodedFragment);
            }

            return result.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("URL 解析失败: " + url, e);
        } catch (Exception e) {
            throw new RuntimeException("URL 编码失败: " + url, e);
        }
    }
}
