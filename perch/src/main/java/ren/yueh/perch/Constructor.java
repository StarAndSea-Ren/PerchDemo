/**
 * File name: Constructor
 * Author: Yueh
 * Date: 2016/12/27 15:09
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import org.jsoup.nodes.Document;

/**
 * Description：此构造器将Html元素与原生Android view模板匹配，最终产出解析完成的view
 * <p>
 * Edit date:
 * Edit content:
 */
public class Constructor {
    //Html文档连接
    private String htmlUrl;

    //Html元素和Android原生View模板的关系适配器
    private RelationsAdapter adapter;

    //界面可展示监听器，呈现引擎工作完成后会调用此监听告知调用者解析过程已完成
    private OnDisplayableListener displayableListener;

    //解析Html得到的DOM tree
    private Document htmlDoc;

    public Constructor(String htmlUrl, RelationsAdapter adapter, OnDisplayableListener displayableListener) {
        this.htmlUrl = htmlUrl;
        this.adapter = adapter;
        this.displayableListener = displayableListener;
        initData();
    }


    /**
     * 连接Html元素和Android原生View模板
     */
    public void attach() {

    }

    /**
     * 初始化数据
     * 对htmlUrl 和 RelationsAdapter做判空处理
     * 根据htmlUrl 得到 html document
     */
    private void initData() {
        if (htmlUrl == null) {
            throw new NullPointerException("Html url is null");
        }
        if (adapter == null) {
            throw new NullPointerException("Relations adapter is null");
        }
        try {
            htmlDoc = HTMLParser.getHtmlDoc(htmlUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
