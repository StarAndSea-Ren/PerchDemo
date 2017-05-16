package ren.yueh.perchdemo.swustsc.nodeview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuehuaren.BaseNodeGroupHolder;
import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodeListViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ren.yueh.perchdemo.swustsc.NewsPageActivity;
import ren.yueh.perchdemo.swustsc.adapter.NewsListViewAdapter;

import static com.yuehuaren.BaseNodeHolder.TEXT_KEY;

/**
 * Created by yuehuaren on 2017/4/24.
 */

public class NewsListNodeViewAdapter extends BaseNodeListViewAdapter<BaseNodeGroupHolder, RecyclerView> {

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
    protected void notifyItemMatch(List<Map<String, String>> nodeResultsData) {
        Map<String, String> tempMap = new HashMap<>();
        for (int i = 0; i < nodeResultsData.size(); i++) {
            if (NewsTitleNodeHolder.class.getName().equals(nodeResultsData.get(i).get(BaseNodeHolder.HOLDER_CLASS_NAME))) {
                tempMap.putAll(nodeResultsData.get(i));
            } else if (NewsDateNodeHolder.class.getName().equals(nodeResultsData.get(i).get(BaseNodeHolder.HOLDER_CLASS_NAME))) {
                String dateStr = nodeResultsData.get(i).get(TEXT_KEY);
                tempMap.put("date", dateStr);
            }
        }
        mData.add(tempMap);
    }

    @Override
    protected void onAttachFinished() {
        super.onAttachFinished();
        mRecyclerView.getAdapter().notifyDataSetChanged();
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
    protected BaseNodeGroupHolder onCreateNodeHolder() {
        return new GroupHolder();
    }

    @Override
    protected RecyclerView onCreateViewModule(Context context) {
        return mRecyclerView;
    }

    public static class GroupHolder extends BaseNodeGroupHolder {

        @Override
        protected List<BaseNodeHolder> createSonNodeHolders() {
            return Arrays.asList(new NewsTitleNodeHolder(), new NewsDateNodeHolder());
        }

        @Override
        protected String createRootTag() {
            return "div";
        }

        @Override
        protected Map<String, String> createRootKnownAttrs() {
            Map<String, String> attr = new HashMap<>();
            attr.put("class", "newsSummarytitle");
            return attr;
        }
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
            return attr;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY);
        }
    }
}
