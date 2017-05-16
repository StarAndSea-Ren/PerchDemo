package com.yuehuaren;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class SuperNListViewAdapter<N extends BaseNodeGroupHolder, V extends RecyclerView> extends SuperNVAdapter<V> {
    //缓存匹配的Node产生的Result
    protected List<Map<String, String>> nodeResultsCache = new ArrayList<>();

    public SuperNListViewAdapter(Context context) {
        super(context);
    }

    /**
     * 将从Html中获取到的值和Native ListView关联到一起
     *
     * @param nodeResultsData
     * @param recyclerView
     * @return
     */
    protected abstract void bindListViewData(List<Map<String, String>> nodeResultsData, V recyclerView);

    /**
     * 判断传入的node是否匹配，如果匹配则解析，并将解析结果放入缓存
     *
     * @param node
     */
    public abstract void matchNode(Node node);

    public View getListView() {
        return viewModule;
    }

    /**
     * 所有Node节点遍历完成时被调用，通知ListView刷新界面
     */
    public void notifyBindListViewData() {
        final List<Map<String, String>> temp = new ArrayList<>();
        temp.addAll(nodeResultsCache);
        bindListViewData(temp, viewModule);
        nodeResultsCache.clear();
    }
}
