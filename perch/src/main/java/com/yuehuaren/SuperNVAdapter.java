package com.yuehuaren;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * HTML Node条件与Native View模板的转换适配器基类
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class SuperNVAdapter<N extends BaseNodeHolder, V extends View> {
    protected V viewModule;
    protected List<N> nodeHolders;
    protected Context context;

    public SuperNVAdapter(Context context) {
        this.context = context;
    }

    public void init() {
        viewModule = onCreateViewModule(context);
        nodeHolders = onCreateNodeHolders();
    }

    /**
     * 当HTML文档中所有节点遍历完毕，不会再调用attachView()时，此函数被调用
     */
    protected void onAttachFinished() {
    }

    /**
     * 录入节点条件集合
     *
     * @return
     */
    protected abstract List<N> onCreateNodeHolders();

    /**
     * 录入View模板
     *
     * @param context
     * @return
     */
    protected abstract V onCreateViewModule(Context context);


}
