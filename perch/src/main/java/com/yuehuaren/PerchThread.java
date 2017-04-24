package com.yuehuaren;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/22.
 */

public class PerchThread extends Thread {

    //超时时间
    private int mTimeOut = 60000;
    //网页地址
    private String mHtmlUrl;
    //根节点tag
    private String mRootTag;
    //根节点属性集合
    private Map<String, String> mRootAttrs;

    //BaseNodeViewAdapter集合
    private List<BaseNodeViewAdapter> mAdapters;

    //解析结果view集合
    private List<View> mResultViews = new ArrayList<>();
    //解析结果view集合应该放入的容器
    private ViewGroup mContainerView;

    public void setTimeOut(int timeOut) {
        this.mTimeOut = timeOut;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.mHtmlUrl = htmlUrl;
    }

    public void setRootTag(String rootTag) {
        this.mRootTag = rootTag;
    }

    public void setRootAttrs(Map<String, String> rootAttrs) {
        this.mRootAttrs = rootAttrs;
    }

    public void setAdapters(List<BaseNodeViewAdapter> adapters) {
        this.mAdapters = adapters;
    }

    public void setContainerView(ViewGroup containerView) {
        this.mContainerView = containerView;
    }

    @Override
    public void run() {
        super.run();
        try {
            doWork();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 主要工作流程
     */
    private void doWork() throws IOException {
        if (TextUtils.isEmpty(mHtmlUrl) || mAdapters == null || mAdapters.isEmpty()) {
            return;
        }

        //从网页加载html文档
        Document doc = downloadDoc(mHtmlUrl, mTimeOut);

        //根据剪枝条件找到根节点
        Element rootElem = getRootElement(doc, mRootTag, mRootAttrs);

        //遍历匹配所有节点
        loopElemSet(rootElem.getAllElements());

        //界面渲染
        renderView();
    }

    /**
     * 从网络下载HTML文档
     *
     * @param url     链接
     * @param timeOut 超时时间
     * @return
     * @throws IOException
     */
    private Document downloadDoc(String url, int timeOut) throws IOException {
        Document doc = Jsoup.connect(mHtmlUrl).timeout(timeOut).get();
        return doc;
    }

    /**
     * 遍历所有节点，判断是否为有用的节点，有则进行取值、关联view模板的操作
     *
     * @param elemSet
     */
    private void loopElemSet(Elements elemSet) {

        for (int i = 0; i < elemSet.size(); i++) {
            Element itemElem = elemSet.get(i);
            for (int j = 0; j < mAdapters.size(); j++) {
                BaseNodeViewAdapter itemAdapter = mAdapters.get(j);
                View view = itemAdapter.matchNodeView(itemElem);
                if (view != null) {
                    mResultViews.add(view);
                }
            }
        }
    }

    /**
     * 将view集合放入容器中，刷新
     */
    private void renderView() {
        if (mContainerView == null || mResultViews == null || mResultViews.isEmpty()) {
            return;
        }
        LinearLayout page = new LinearLayout(mContainerView.getContext());
        final ScrollView scrollView = new ScrollView(mContainerView.getContext());
        scrollView.addView(page);
        scrollView.setVerticalScrollBarEnabled(false);
        page.setOrientation(LinearLayout.VERTICAL);
        //将view集合放入容器
        for (int i = 0; i < mResultViews.size(); i++) {
            page.addView(mResultViews.get(i));
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                mContainerView.removeAllViews();
                mContainerView.addView(scrollView);
            }
        });
    }

    /**
     * 如果有剪枝条件则根据剪枝条件获取根节点
     * 如果有多个节点满足则返回第一个节点
     *
     * @param rootTag
     * @param rootAttrs
     * @return
     */
    private Element getRootElement(Document doc, String rootTag, Map<String, String> rootAttrs) {
        if (doc == null) {
            return null;
        }
        if (TextUtils.isEmpty(rootTag) && (rootAttrs == null || rootAttrs.size() == 0)) {
            return doc;
        }

        Elements mayBeRootElemSet = doc.getAllElements();

        //根据tag条件剪枝
        if (!TextUtils.isEmpty(rootTag)) {
            mayBeRootElemSet = mayBeRootElemSet.select(rootTag);
        }

        //根据属性条件剪枝
        if (rootAttrs != null && rootAttrs.size() != 0) {

            Elements tempElemSet = new Elements();

            Iterator<Map.Entry<String, String>> iterator = rootAttrs.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();

                //只有属性key
                if (TextUtils.isEmpty(entry.getValue())) {
                    for (int i = mayBeRootElemSet.size() - 1; i >= 0; i--) {
                        if (mayBeRootElemSet.get(i).hasAttr(entry.getKey())) {
                            tempElemSet = mayBeRootElemSet.get(i).getElementsByAttribute(entry.getKey());
                        }
                    }
                }
                //拥有属性key和value
                else {
                    for (int i = mayBeRootElemSet.size() - 1; i >= 0; i--) {
                        if (mayBeRootElemSet.get(i).getElementsByAttributeValue(entry.getKey(), entry.getValue()).size() != 0) {
                            tempElemSet = mayBeRootElemSet.get(i).getElementsByAttributeValue(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
            mayBeRootElemSet = tempElemSet;
        }

        if (mayBeRootElemSet != null && mayBeRootElemSet.size() > 0) {
            return mayBeRootElemSet.get(0);
        } else {
            return null;
        }
    }
}
