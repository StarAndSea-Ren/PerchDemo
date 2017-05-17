package ren.yueh.perchdemo.baijia.nodeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yuehuaren.nodeholder.BaseNodeGroupHolder;
import com.yuehuaren.nodeholder.BaseNodeHolder;
import com.yuehuaren.adapter.BaseNodeListViewAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by yueh on 2017/5/15.
 */

public class BaiJiaNodeViewAdapter extends BaseNodeListViewAdapter<BaseNodeGroupHolder,RecyclerView> {
    public BaiJiaNodeViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void notifyItemMatch(List<Map<String, String>> nodeResultsData) {

    }

    @Override
    protected BaseNodeGroupHolder onCreateNodeHolder() {
        return null;
    }



    @Override
    protected RecyclerView onCreateViewModule(Context context) {
        return null;
    }

    public static class NodeHolder extends BaseNodeGroupHolder{

        @Override
        protected List<BaseNodeHolder> createSonNodeHolders() {
            return null;
        }

        @Override
        protected String createRootTag() {
            return null;
        }

        @Override
        protected Map<String, String> createRootKnownAttrs() {
            return null;
        }

        @Override
        protected String createTag() {
            return null;
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            return null;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return null;
        }
    }
}
