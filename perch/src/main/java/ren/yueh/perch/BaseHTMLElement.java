/**
 * File name: BaseHTMLElement
 * Author: Yueh
 * Date: 2016/12/27 19:22
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/**
 * Description：
 * <p>
 * Edit date:
 * Edit content:
 */
public abstract class BaseHTMLElement {
    protected String tagName;
    protected Attributes attributes;

    protected Element element;
    protected String content;

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public BaseHTMLElement addAttr(String key, String value) {
        if (attributes == null) {
            attributes = new Attributes();
        }
        Attribute attr = new Attribute(key, value);
        attributes.put(attr);
        return this;
    }

    // todo 如何优雅地定义应该从哪获取内容
    /**
     public void getContentFromAttributes(String attrKey) {
     content = element.attr(attrKey);
     }
     public void getContentFromText() {
     content = element.text();
     }
     */
}