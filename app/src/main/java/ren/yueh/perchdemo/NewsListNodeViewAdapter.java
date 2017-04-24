package ren.yueh.perchdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodeViewAdapter;

import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/24.
 */

public class NewsListNodeViewAdapter extends BaseNodeViewAdapter<BaseNodeHolder,RecyclerView> {
    public NewsListNodeViewAdapter(Context context) {
        super(context);

    }

    @Override
    protected RecyclerView attachView(Map<String, String> nodeResult, RecyclerView view) {
        return null;
    }

    @Override
    protected RecyclerView onCreateViewModule(Context context) {
        return null;
    }

    @Override
    protected BaseNodeHolder onCreateNodeHolder() {
        return null;
    }
}
