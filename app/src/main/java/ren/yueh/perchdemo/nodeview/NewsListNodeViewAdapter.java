package ren.yueh.perchdemo.nodeview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodesListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ren.yueh.perchdemo.NewsPageActivity;
import ren.yueh.perchdemo.adapter.NewsListViewAdapter;

import static com.yuehuaren.BaseNodeHolder.TEXT_KEY;

/**
 * Created by yuehuaren on 2017/4/24.
 */

public class NewsListNodeViewAdapter extends BaseNodesListViewAdapter<BaseNodeHolder, RecyclerView> {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mManager;
    private NewsListViewAdapter mAdapter;
    private List<Map<String, String>> mData;
    private Activity mParentActivity;

    public NewsListNodeViewAdapter(Context context) {
        super(context);
        mData = new ArrayList<>();
        mRecyclerView = new RecyclerView(context);
        mAdapter = new NewsListViewAdapter(context, mData);
        mManager = new LinearLayoutManager(context);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter.setItemClickListener(new NewsListViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String url = mData.get(position).get("href");
                goNewsPage(url);
            }
        });
    }


    @Override
    protected List<BaseNodeHolder> onCreateNodeHolders() {
        return Arrays.asList(new NewsTitleNodeHolder(), new NewsDateNodeHolder());
    }

    public void setParentActivity(Activity activity) {
        mParentActivity = activity;
    }

    private void goNewsPage(String url) {
        Intent intent = new Intent();
        intent.setClass(mParentActivity, NewsPageActivity.class);
        intent.putExtra("url", url);
        mParentActivity.startActivity(intent);
    }

    @Override
    protected void bindListViewData(List<Map<String, String>> nodeResultsData, RecyclerView recyclerView) {
        List<Map<String, String>> tempList = new ArrayList<>();
        Map<String, String> tempMap = null;
        for (int i = 0; i < nodeResultsData.size(); i++) {
            if (nodeResultsData.get(i).size() == 2) {
                tempMap = new HashMap<>();
                tempMap.putAll(nodeResultsData.get(i));
            } else if (nodeResultsData.get(i).size() == 1) {
                String dateStr = nodeResultsData.get(i).get(TEXT_KEY);
                Map<String, String> map = new HashMap<>();
                map.put("date", dateStr);
                tempMap.putAll(map);
                tempList.add(tempMap);
                tempMap = null;
            }
        }
        mData.clear();
        mData.addAll(tempList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected RecyclerView onCreateViewModule(Context context) {
        return mRecyclerView;
    }

    public static class NewsTitleNodeHolder extends BaseNodeHolder {

        @Override
        protected String createTag() {
            return "a";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            return null;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList("href", "title");
        }
    }

    public static class NewsDateNodeHolder extends BaseNodeHolder {

        @Override
        protected String createTag() {
            return "span";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String, String> attr = new HashMap<>();
            attr.put("class", "newspostdate");
            return null;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY);
        }
    }
}
