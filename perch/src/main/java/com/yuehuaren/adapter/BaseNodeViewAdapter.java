package com.yuehuaren.adapter;

import android.content.Context;
import android.view.View;

import com.yuehuaren.nodeholder.BaseNodeHolder;

import org.jsoup.nodes.Node;

import java.util.Arrays;
import java.util.List;

/**
 * HTML Node条件与Native View模板的转换适配器
 * 一个Node条件对应一个View模板，此时每找到一个和条件匹配的节点则生成一个View
 * Created by yuehuaren on 2017/4/21.
 */

public abstract class BaseNodeViewAdapter<N extends BaseNodeHolder, V extends View> extends SuperNViewAdapter<N, V> {

    public BaseNodeViewAdapter(Context context) {
        super(context);
    }

    /**
     * 录入一个节点条件
     *
     * @return
     */
    protected abstract N onCreateNodeHolder();

    @Override
    protected List<N> onCreateNodeHolders() {
        return Arrays.asList(onCreateNodeHolder());
    }

    /**
     * 匹配Node和View模板
     *
     * @param node
     */
    public View matchNodeView(Node node) {
        if (nodeHolders != null && nodeHolders.size() > 0) {
            BaseNodeHolder nodeHolder = nodeHolders.get(0);
            if (nodeHolder.isSuitableNode(node)) {
                nodeHolder.putResultAttrValue(node);
                return attachView(nodeHolder.getResultAttrValues(), onCreateViewModule(context));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


}
