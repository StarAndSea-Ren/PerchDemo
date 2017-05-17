package com.yuehuaren.adapter;

import android.content.Context;
import android.view.View;

import com.yuehuaren.nodeholder.BaseNodeHolder;

import org.jsoup.nodes.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class SuperNViewAdapter<N extends BaseNodeHolder, V extends View> extends SuperNVAdapter<V> {
    protected List<N> nodeHolders;

    public SuperNViewAdapter(Context context) {
        super(context);
        nodeHolders = onCreateNodeHolders();
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
     * 匹配Node和View模板
     *
     * @param node
     */
    public abstract View matchNodeView(Node node);

    /**
     * 录入节点条件集合
     *
     * @return
     */
    protected abstract List<N> onCreateNodeHolders();
}
