import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.List;

/**
 * @author wuzhong
 * @create 2018-11-14 9:41
 */
public class TestDom4jXpath {
    public static void main(String[] args) throws Exception{
        test1();
    }

    public static void test1() throws Exception {
        Document document = Dom4jUtils.getDocument(Dom4jUtils.PATH);

        List<Node> list = document.selectNodes("//name");

        for (Node node : list) {
            String s = node.getText();
            System.out.println(s);
        }
    }
}
