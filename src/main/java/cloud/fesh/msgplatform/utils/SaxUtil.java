package cloud.fesh.msgplatform.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SaxUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SaxUtil.class);

    private SaxUtil() {}

    public static Map<String, String> getAttrByXmlStr(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(xml);
            Element rootElement = document.getRootElement();
            for (Attribute attribute : rootElement.attributes()) {
                map.put(attribute.getName(), attribute.getValue());
            }
        } catch (Exception e) {
            LOG.error("xml解析失败: ", e);
        }
        return map;
    }
}
