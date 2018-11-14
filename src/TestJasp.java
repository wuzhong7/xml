import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author wuzhong
 * @create 2018-11-12 21:15
 */
public class TestJasp {
    public static void main(String[] args) throws Exception {
        selectAll();
        System.out.println("-------");
        addSex();
        modifySex();
        removeSex();
        listElement();
    }

    public static void selectAll() throws  Exception {
        // 创建解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // 创建解析器
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        // 解析 xml 返回 document
        Document document = builder.parse("web/WEB-INF/ces.xml");

        // 得到 name 元素
        NodeList list = document.getElementsByTagName("name");

        // 遍历集合
        for (int i = 0; i < list.getLength(); i++) {
            // 得到 name 元素里面的值
            Node name1 = list.item(i);
            String s = name1.getTextContent();
            System.out.println(s);
        }
    }

    public static void addSex() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("web/WEB-INF/ces.xml");

        NodeList list = document.getElementsByTagName("p1");
        Node p1 = list.item(0);
        Element sex1 = document.createElement("sex");
        Text text1 = document.createTextNode("nv");
        sex1.appendChild(text1);
        p1.appendChild(sex1);

        // 回写 xml
        TransformerFactory formerFactory = TransformerFactory.newInstance();
        Transformer transformer = formerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("web/WEB-INF/ces.xml"));
    }

    public static void modifySex() throws Exception{
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("web/WEB-INF/ces.xml");

        Node sex1 = document.getElementsByTagName("sex").item(0);
        sex1.setTextContent("nan");

        TransformerFactory formerFactory = TransformerFactory.newInstance();
        Transformer transformer = formerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("web/WEB-INF/ces.xml"));
    }

    public static void removeSex() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("web/WEB-INF/ces.xml");

        Node sex1 = document.getElementsByTagName("sex").item(0);
        Node parent1 = sex1.getParentNode();
        parent1.removeChild(sex1);

        TransformerFactory formerFactory = TransformerFactory.newInstance();
        Transformer transformer = formerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult("web/WEB-INF/ces.xml"));
    }

    public static void listElement() throws Exception {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse("web/WEB-INF/ces.xml");

        list(document);
    }

    public static void list(Node node) {
        // 判断是元素类型时才打印
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println(node.getNodeName());
        }

        // 得到一层子节点
        NodeList list = node.getChildNodes();
        // 遍历 list
        for (int i = 0; i < ((NodeList) list).getLength(); i++) {
            // 得到每一个节点
            Node node1 = list.item(i);
            // 继续得到 node1 节点  node1.getChildNodes()
            list(node1);
        }
    }
}

