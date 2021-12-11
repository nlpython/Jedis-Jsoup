package JsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 *     2. Document: 文档对象, 代表内存中的dom树
 *         (1) 获取Element对象
 *              1) getElementByTag(String name) 根据标签名称获取元素对象集合
 *              2) getElementByAttribute(String key)  根据属性名称来获取元素对象集合
 *              3) getElementByAttributeValue(String key, String value)  根据对应的属性名和属性值获取元素对象
 *              4) getElementById(String Id)  根据Id属性值来获取唯一的Element对象
 */

public class Demo2 {
    public static void main(String[] args) throws IOException {
        // 1.获取path路径
        String path = Demo2.class.getClassLoader().getResource("a.xml").getPath();
        // 2.获取Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 3.获取对象
        // (1)获取所有的student对象
        Elements elements = document.getElementsByTag("student");
        System.out.println(elements.text());
        System.out.println("----------------");

        // (2)获取所有属性名为number的元素对象
        Elements elements1 = document.getElementsByAttribute("number");
        System.out.println(elements1);
        System.out.println("----------------");

        // (3)获取number属性值为01的元素对象
        Elements elements2 = document.getElementsByAttributeValue("number", "001");
        System.out.println(elements2);
        System.out.println("----------------");

        // (4)获取id属性值为02的对象
        Element elementById = document.getElementById("itcast");
        System.out.println(elementById);

    }
}
