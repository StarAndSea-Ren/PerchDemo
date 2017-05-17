package com.yuehuaren.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * HTML Node条件与Native View模板的转换适配器基类
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class SuperNVAdapter<V extends View> {
    protected V viewModule;

    protected Context context;

    public SuperNVAdapter(Context context) {
        this.context = context;
    }

    public void init() {
        viewModule = onCreateViewModule(context);
    }

    /**
     * 当HTML文档中所有节点遍历完毕，不会再调用attachView()时，此函数被调用
     */
    public void onAttachFinished() {
    }

    /**
     * 录入View模板
     *
     * @param context
     * @return
     */
    protected abstract V onCreateViewModule(Context context);
}
