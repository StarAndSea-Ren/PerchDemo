package ren.yueh.perchdemo.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ren.yueh.perchdemo.R;
import ren.yueh.perchdemo.main.adapter.NewsChannelAdapter;

/**
 * 首页新闻频道入口界面
 */
public class HomeActivity extends AppCompatActivity {

    private RecyclerView channelListView;
    private RecyclerView.LayoutManager manager;
    private NewsChannelAdapter adapter;
    private List<NewsChannelAdapter.Channel> channels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        channelListView = (RecyclerView) findViewById(R.id.news_channel_listview);
        manager = new GridLayoutManager(this, 3);
        adapter = new NewsChannelAdapter(this, channels);
        adapter.setItemClickListener(new NewsChannelAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                NewsChannelAdapter.Channel channel = channels.get(position);
            }
        });
        channelListView.setAdapter(adapter);
        channelListView.setLayoutManager(manager);
    }

    private void initData() {
        channels.clear();
        channels.addAll(Arrays.asList(new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "我的收藏")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "计科学院")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "界面新闻")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "ZAKER")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "36Kr")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "国家地理")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "品玩")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "百度百家")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "果壳网")
                , new NewsChannelAdapter.Channel(R.drawable.ic_zhihu, "知乎日报")));
    }
}
