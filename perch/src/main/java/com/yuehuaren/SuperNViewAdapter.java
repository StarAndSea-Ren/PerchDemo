package com.yuehuaren;

import android.content.Context;
import android.view.View;

import org.jsoup.nodes.Node;

import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/25.
 */

public abstract class SuperNViewAdapter<N extends BaseNodeHolder, V extends View> extends SuperNVAdapter<N, V> {
    public SuperNViewAdapter(Context context) {
        super(context);
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
}