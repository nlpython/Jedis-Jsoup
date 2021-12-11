package JsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Jsoup快速入门
 */

public class Demo1 {
    public static void main(String[] args) throws IOException {
        // 1.获取document对象
        // 1.1获取xml文件path
        String path = Demo1.class.getClassLoader().getResource("a.xml").getPath();
        // 1.2解析xml文档, 加载文档进内存, 获取dom树-->Document
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 2. 获取元素对象Element
        Elements elements = document.getElementsByTag("name");
//        System.out.println(elements.size());  // 2

        // 3.获取第一个name对象
        Element element = elements.get(0);

        // 4.获取数据
        String name = element.text();
        System.out.println(name);
    }

    @Test
    public void testParse01() {
        String html = "<?xml version=\"1.0\" encoding='UTF-8' ?>\n" +
                "\n" +
                "<students>\n" +
                "    <student number=\"001\">\n" +
                "        <name>Tom</name>\n" +
                "        <age>19</age>\n" +
                "        <sex>male</sex>\n" +
                "    </student>\n" +
                "\n" +
                "    <student number=\"002\">\n" +
                "        <name>Amy</name>\n" +
                "        <age>18</age>\n" +
                "        <sex>female</sex>\n" +
                "    </student>\n" +
                "</students>";
        Document document = Jsoup.parse(html);
        System.out.println(document);
    }

    @Test
    public void testParse02() throws IOException {
        URL url = new URL("https://www.baidu.com/");
        Document document = Jsoup.parse(url, 10000); // 10000ms的超时时间, 若此时间内未能打开, 则失败
        System.out.println(document);
    }
}


