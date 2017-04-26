package com.yuehuaren;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import org.jsoup.nodes.Node;

import java.util.Arrays;
import java.util.List;


/**
 * HTML Node条件与Native View模板的转换适配器
 * 一个Node条件对应一个ListView模板，此时每找到一个和条件匹配的节点则缓存起来，所有节点遍历结束以后统一生成一个ListView
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class BaseNodeListViewAdapter<N extends BaseNodeHolder, V extends RecyclerView> extends SuperNListViewAdapter<N, V> {

    public BaseNodeListViewAdapter(Context context) {
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
     * 判断传入的node是否匹配，如果匹配则解析，并将解析结果放入缓存
     *
     * @param node
     */
    public void matchNode(Node node) {
        if (nodeHolders != null && nodeHolders.size() > 0) {
            BaseNodeHolder nodeHolder = nodeHolders.get(0);
            if (nodeHolder.isSuitableNode(node)) {
                nodeHolder.putResultAttrValue(node);
                nodeResultsCache.add(nodeHolder.getResultAttrValues());
            }
        }
    }
}
