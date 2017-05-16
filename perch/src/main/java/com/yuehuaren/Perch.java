package com.yuehuaren;

import android.util.Log;
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

    //SuperNViewAdapter集合
    private List<SuperNViewAdapter> mViewAdapters = new ArrayList<>();

    //SuperNListViewAdapter
    private BaseNodeListViewAdapter mListViewAdapters;

    //解析结果view集合应该放入的容器
    private ViewGroup mContainerView;

    //状态回调
    private PerchCallBack mCallBack;

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

    public Perch addNodeViewAdapter(SuperNViewAdapter adapter) {
        if (mListViewAdapters != null) {
            Log.e("Perch", "NListViewAdapter已存在,NViewAdapter不能与其同时存在");
            if (mCallBack != null) {
                mCallBack.onFail("NListViewAdapter已存在,NViewAdapter不能与其同时存在");
            }
            return this;
        }
        this.mViewAdapters.add(adapter);
        return this;
    }

    public Perch setNodeListViewAdapter(BaseNodeListViewAdapter adapter) {
        if (mViewAdapters.size() != 0) {
            Log.e("Perch", "NViewAdapter已存在,NListViewAdapter不能与其同时存在");
            if (mCallBack != null) {
                mCallBack.onFail("NViewAdapter已存在,NListViewAdapter不能与其同时存在");
            }
            return this;
        }
        this.mListViewAdapters = adapter;
        return this;
    }

    public Perch setViewContainer(ViewGroup view) {
        this.mContainerView = view;
        return this;
    }

    public Perch setCallBack(PerchCallBack callBack) {
        this.mCallBack = callBack;
        return this;
    }

    public void start() {
        PerchThread thread = new PerchThread();
        if (mTimeOut >= 0) {
            thread.setTimeOut(mTimeOut);
        }

        thread.setCallBack(mCallBack);

        thread.setHtmlUrl(mHtmlUrl);

        thread.setRootAttrs(mRootAttrs);
        thread.setRootTag(mRootTag);

        thread.setViewAdapters(mViewAdapters);
        thread.setListViewAdapter(mListViewAdapters);
        thread.setContainerView(mContainerView);

        thread.start();
    }

    public interface PerchCallBack {
        void onSuccess();

        void onFail(String msg);
    }
}
