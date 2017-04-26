package com.yuehuaren;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.jsoup.nodes.Node;

/**
 * HTML Node条件与Native View模板的转换适配器
 * 多个Node条件对应一个View模板，此时每找到一个和条件集合中任意条件匹配的节点则缓存起来，所有节点遍历结束以后统一生成一个ListView
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class BaseNodesListViewAdapter<N extends BaseNodeHolder, V extends RecyclerView> extends SuperNListViewAdapter<N, V> {

    public BaseNodesListViewAdapter(Context context) {
        super(context);
    }

    /**
     * 判断传入的node是否匹配，如果匹配则解析，并将解析结果放入缓存
     *
     * @param node
     */
    public void matchNode(Node node) {
        if (nodeHolders != null && nodeHolders.size() > 0) {
            for (int i = 0; i < nodeHolders.size(); i++) {
                BaseNodeHolder nodeHolder = nodeHolders.get(i);
                if (nodeHolder.isSuitableNode(node)) {
                    nodeHolder.putResultAttrValue(node);
                    nodeResultsCache.add(nodeHolder.getResultAttrValues());
                    break;
                }
            }
        }
    }
}
