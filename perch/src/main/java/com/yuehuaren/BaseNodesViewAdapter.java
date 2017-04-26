package com.yuehuaren;

import android.content.Context;
import android.view.View;

import org.jsoup.nodes.Node;

/**
 * HTML Node条件与Native View模板的转换适配器
 * 多个Node条件对应一个View模板，此时每找到一个和条件集合中任意条件匹配的节点则生成一个View
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class BaseNodesViewAdapter<N extends BaseNodeHolder, V extends View> extends SuperNViewAdapter<N, V> {
    public BaseNodesViewAdapter(Context context) {
        super(context);
    }

    /**
     * 匹配Node和View模板
     *
     * @param node
     */
    public View matchNodeView(Node node) {
        if (nodeHolders != null && nodeHolders.size() > 0) {
            for (int i = 0; i < nodeHolders.size(); i++) {
                BaseNodeHolder nodeHolder = nodeHolders.get(i);
                if (nodeHolder.isSuitableNode(node)) {
                    nodeHolder.putResultAttrValue(node);
                    return attachView(nodeHolder.getResultAttrValues(), onCreateViewModule(context));
                }
            }
            return null;
        } else {
            return null;
        }
    }
}
