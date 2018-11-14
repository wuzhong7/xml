import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;

/**
 * @author wuzhong
 * @create 2018-11-14 7:32
 */
public class Dom4jUtils {
    public static final String PATH = "web/WEB-INF/ces.xml";
    public static Document getDocument(String path) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read("web/WEB-INF/ces.xml");
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void xmlWrites(String  path, Document document) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("web/WEB-INF/ces.xml"), format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
