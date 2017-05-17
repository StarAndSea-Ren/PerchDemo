package com.yuehuaren.adapter;

import android.content.Context;
import android.view.View;

import com.yuehuaren.nodeholder.BaseNodeGroupHolder;

import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;


/**
 * HTML Node条件与Native View模板的转换适配器
 * 一个Node条件对应一个ListView模板，此时每找到一个和条件匹配的节点则缓存起来，所有节点遍历结束以后统一生成一个ListView
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class BaseNodeListViewAdapter<N extends BaseNodeGroupHolder, V extends View> extends SuperNVAdapter<V> {

    private BaseNodeGroupHolder nodeGroupHolder;

    public BaseNodeListViewAdapter(Context context) {
        super(context);
        nodeGroupHolder = onCreateNodeHolder();
    }

    /**
     * 从item中找到一项匹配
     *
     * @param nodeResultsData
     * @return
     */
    protected abstract void notifyItemMatch(List<Map<String, String>> nodeResultsData);


    public V getListView() {
        return viewModule;
    }


    /**
     * 录入一个节点条件
     *
     * @return
     */
    protected abstract N onCreateNodeHolder();

    /**
     * 判断传入的node是否匹配，如果匹配则解析，并将解析结果放入缓存
     *
     * @param node
     */
    public void matchNode(Element node) {
        if (nodeGroupHolder != null && !nodeGroupHolder.isRootNodeNull()) {
            //Node满足录入的Root Node条件
            if (nodeGroupHolder.isSuitableRootNode(node)) {
                nodeGroupHolder.loopSonNodeHolders(node.getAllElements());
                notifyItemMatch(nodeGroupHolder.getResult());
            }
        }
    }
}
