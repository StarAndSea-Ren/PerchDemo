package com.yuehuaren;

import android.content.Context;
import android.view.View;

/**
 * 承载NativeView和View需要Html提供的值的基类
 * Created by yuehuaren on 2017/4/21.
 */

public abstract class BaseViewHolder<V extends View> {
    /**
     * Native View ,View应该包含了Perch使用者自定义的样式信息
     */
    protected V view;
    private Context context;

    public BaseViewHolder(Context context) {
        this.context = context;
    }

    /**
     * 录入View,需子类实现
     *
     * @param context
     * @return
     */
    protected abstract V createView(Context context);

    public V getView() {
        return createView(context);
    }
}
