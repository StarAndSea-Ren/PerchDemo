/**
 * File name: Relation
 * Author: Yueh
 * Date: 2016/12/26 17:10
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import android.view.View;

import org.jsoup.nodes.Node;

/**
 * Description：Html元素与View模板的对应关系
 * <p>
 * Edit date:
 * Edit content:
 */
public class Relation {
    //Android原生view模板
    private View androidViewTemplate;
    //Html元素
    private Node htmlNode;

    public Relation(View androidViewTemplate, Node htmlNode) {
        this.androidViewTemplate = androidViewTemplate;
        this.htmlNode = htmlNode;
    }

    public View getAndroidViewTemplate() {
        return androidViewTemplate;
    }

    public void setAndroidViewTemplate(View androidViewTemplate) {
        this.androidViewTemplate = androidViewTemplate;
    }

    public Node getHtmlNode() {
        return htmlNode;
    }

    public void setHtmlNode(Node htmlNode) {
        this.htmlNode = htmlNode;
    }
}
