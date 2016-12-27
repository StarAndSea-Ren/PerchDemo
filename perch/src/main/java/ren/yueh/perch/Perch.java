/**
 * File name: Perch
 * Author: Yueh
 * Date: 2016/12/26 17:09
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

/**
 * Description：Perch 入口
 * <p>
 * Edit date:2016-12-26 17:10:55
 * Edit content:
 */
public class Perch {

    //Html文档连接
    private String htmlUrl;

    //Html元素和Android原生View模板的关系适配器
    private RelationsAdapter adapter;

    //界面可展示监听器，呈现引擎工作完成后会调用此监听告知调用者解析过程已完成
    private OnDisplayableListener displayableListener;

    public static Perch create() {
        return new Perch();
    }

    /**
     * 初始化关系适配器
     *
     * @param adapter
     * @return
     */
    public Perch setRelationsAdapter(RelationsAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /**
     * 初始化Html文档连接
     *
     * @param url
     * @return
     */
    public Perch setHtmlUrl(String url) {
        this.htmlUrl = url;
        return this;
    }

    /**
     * 初始化界面可展示监听器
     *
     * @param listener
     * @return
     */
    public Perch setOnDisplayableListener(OnDisplayableListener listener) {
        this.displayableListener = listener;
        return this;
    }

    /**
     * 开始解析
     */
    public void start() {
        Constructor constructor = new Constructor(htmlUrl,adapter,displayableListener);
    }
}
