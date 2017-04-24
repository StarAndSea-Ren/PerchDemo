package com.yuehuaren;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/22.
 */

public class Perch {
    //超时时间
    private int mTimeOut = Integer.MIN_VALUE;
    //网页地址
    private String mHtmlUrl;
    //根节点tag
    private String mRootTag;
    //根节点属性集合
    private Map<String, String> mRootAttrs;

    //BaseNodeViewAdapter集合
    private List<BaseNodeViewAdapter> mAdapters = new ArrayList<>();

    //解析结果view集合应该放入的容器
    private ViewGroup mContainerView;

    public static Perch create() {
        return new Perch();
    }

    public Perch setTimeOut(int times) {
        this.mTimeOut = times;
        return this;
    }

    public Perch setHtmlUrl(String url) {
        this.mHtmlUrl = url;
        return this;
    }

    public Perch setRootTag(String tag) {
        this.mRootTag = tag;
        return this;
    }

    public Perch setRootAttrs(Map<String, String> rootAttrs) {
        this.mRootAttrs = rootAttrs;
        return this;
    }

    public Perch addNodeViewAdapter(BaseNodeViewAdapter adapter) {
        this.mAdapters.add(adapter);
        return this;
    }

    public Perch setViewContainer(ViewGroup view) {
        this.mContainerView = view;
        return this;
    }

    public void start() {
        PerchThread thread = new PerchThread();
        if (mTimeOut >= 0) {
            thread.setTimeOut(mTimeOut);
        }
        thread.setHtmlUrl(mHtmlUrl);

        thread.setRootAttrs(mRootAttrs);
        thread.setRootTag(mRootTag);

        thread.setAdapters(mAdapters);
        thread.setContainerView(mContainerView);

        thread.start();
    }
}
