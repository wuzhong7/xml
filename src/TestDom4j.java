import com.sun.jdi.Value;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @author wuzhong
 * @create 2018-11-13 19:58
 */
public class TestDom4j {
    public static void main(String[] args) throws Exception {
        selectName();
        addSex();
        addSchool();
        modifyAge();
        getValues();
    }

    public static void selectName() throws Exception {
        // 创建解析器
        SAXReader saxReader = new SAXReader();
        // 得到 document
        Document document = saxReader.read("web/WEB-INF/ces.xml");
        // 得到根节点
        Element root = document.getRootElement();

        // 得到所有的 p1 标签
        List<Element> list = root.elements("p1");

        // 遍历 list 得到每一个 p1
        for (Element element : list) {
            // 得到 name
            Element name1 = element.element("name");
            // 得到 name 里面的值
            String s = name1.getText();
            System.out.println(s);
        }
    }

    public static void addSex() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read("web/WEB-INF/ces.xml");
        Element root = document.getRootElement();
        Element p1 = root.element("p1");
        // 在 p1 元素下面直接添加元素
        Element sex1 = p1.addElement("sex");
        // 在 sex 元素下面添加文本
        sex1.setText("nv");

        // 回写 xml
        OutputFormat format = OutputFormat.createPrettyPrint(); // 有缩进效果
        // 使用类 XMLWriter 直接 new 这个类，传递两个参数
        // 第一个参数是xml文件路径 new FileOutputStream("路径")
        // 第二个参数是格式化类的值
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("web/WEB-INF/ces.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public static void addSchool() {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);
        Element root = document.getRootElement();

        Element p1 = root.element("p1");
        List<Element> list = p1.elements();

        Element school = DocumentHelper.createElement("school");
        school.setText("ecit");
        list.add(1, school);

        Dom4jUtils.xmlWrites("web/WEB-INF/ces.xml", document);
    }

    public static void modifyAge() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read("web/WEB-INF/ces.xml");

        Element root = document.getRootElement();
        Element p1 = root.element("p1");
        Element age1 = p1.element("age");

        age1.setText("300");

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("web/WEB-INF/ces.xml"), format);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public static void getValues() throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);

        Element root = document.getRootElement();
        Element p1 = root.element("p1");
        String s = p1.attributeValue("id");
        System.out.println(s);
    }
}
