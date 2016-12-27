/**
 * File name: HTMLParser
 * Author: Yueh
 * Date: 2016/12/27 16:42
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Description：html元素解析器
 * <p>
 * Edit date:
 * Edit content:
 */
public class HTMLParser {

    /**
     * 从url获取Html文档
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Document getHtmlDoc(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
}
