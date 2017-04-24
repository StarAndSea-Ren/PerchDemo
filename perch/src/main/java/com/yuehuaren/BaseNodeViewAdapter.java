package com.yuehuaren;

import android.content.Context;
import android.view.View;

import org.jsoup.nodes.Node;

import java.util.Map;

/**
 * HTML Node与Native View的转化关系在规则在此类定义
 * Created by yuehuaren on 2017/4/21.
 */

public abstract class BaseNodeViewAdapter<N extends BaseNodeHolder, V extends View> {

    /**
     * Html node
     */
    protected N nodeHolder;

    /**
     * Native View 模板
     */
    protected V viewModule;

    /**
     * Android上下文
     */
    protected Context context;

    public BaseNodeViewAdapter(Context context) {
        nodeHolder = onCreateNodeHolder();
        viewModule = onCreateViewModule(context);
        this.context = context;
    }

    /**
     * 将从Html中获取到的值和Native View关联到一起
     *
     * @param nodeResult
     * @param view
     * @return
     */
    protected abstract V attachView(Map<String, String> nodeResult, V view);

    /**
     * 录入View模板
     *
     * @param context
     * @return
     */
    protected abstract V onCreateViewModule(Context context);

    /**
     * 录入Html条件
     *
     * @return
     */
    protected abstract N onCreateNodeHolder();

    /**
     * 匹配Node和View模板
     *
     * @param node
     */
    public View matchNodeView(Node node) {
        if (nodeHolder.isSuitableNode(node)) {
            nodeHolder.putResultAttrValue(node);
            return attachView(nodeHolder.getResultAttrValues(), onCreateViewModule(context));
        } else {
            return null;
        }

    }

    protected Context getContext() {
        return context;
    }
}
