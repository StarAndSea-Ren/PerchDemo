package ren.yueh.perchdemo;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.Perch;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ren.yueh.perchdemo.nodeview.ImgNodeViewAdapter;
import ren.yueh.perchdemo.nodeview.NewsHeadNodeViewAdapter;
import ren.yueh.perchdemo.nodeview.TextNodeViewAdapter;

public class NewsPageActivity extends AppCompatActivity implements View.OnClickListener {
    private ScrollView mRootScroll;
    private FrameLayout mContainer;
    private String url;
    private SwipeRefreshLayout mSwipe;
    private TextView mNewsTitle;
    private TextView mNewsDate;
    private TextView mNewsTag;
    private TextView mNewsRead;
    private Button mShareBtn;
    private Button mUpTopBtn;

    private TextNodeViewAdapter textNodeViewAdapter;
    private ImgNodeViewAdapter imgNodeViewAdapter;
    private NewsHeadNodeViewAdapter newsHeadNodeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);
        mContainer = (FrameLayout) findViewById(R.id.container);
        mRootScroll = (ScrollView) findViewById(R.id.root_scroll);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mNewsTitle = (TextView) findViewById(R.id.news_title_txt);
        mNewsTag = (TextView) findViewById(R.id.news_tag_txt);
        mNewsDate = (TextView) findViewById(R.id.news_date_txt);
        mNewsRead = (TextView) findViewById(R.id.news_read_txt);
        mShareBtn = (Button) findViewById(R.id.share_btn);
        mUpTopBtn = (Button) findViewById(R.id.up_btn);
        mShareBtn.setOnClickListener(this);
        mUpTopBtn.setOnClickListener(this);

        mSwipe.setRefreshing(true);

        url = getIntent().getStringExtra("url");
        textNodeViewAdapter = new TextNodeViewAdapter(getApplication());
        imgNodeViewAdapter = new ImgNodeViewAdapter(getApplication());
        newsHeadNodeViewAdapter = new NewsHeadNodeViewAdapter(getApplication());

        getNewsPageView();
    }

    private void renderNewsHeader() {
        List<Map<String, String>> data = newsHeadNodeViewAdapter.getData();
        if (data == null || data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            Iterator<Map.Entry<String, String>> iterator = data.get(i).entrySet().iterator();
            String key = "";
            String value = "";
            while (iterator.hasNext()) {
                Map.Entry<String, String> thisMap = iterator.next();
                String keyStr = thisMap.getKey();
                if (keyStr.equals(BaseNodeHolder.TEXT_KEY)) {
                    value = thisMap.getValue();
                } else if (keyStr.equals("class")) {
                    key = thisMap.getValue();
                }
            }
            switch (key) {
                case "contentTitle":
                    mNewsTitle.setText(value);
                    break;
                case "postDate":
                    mNewsDate.setText(value);
                    break;
                case "postCategory":
                    mNewsTag.setText(value);
                    break;
                case "postviews":
                    mNewsRead.setText(value);
                    break;
                default:
                    break;
            }
        }
    }


    private void getNewsPageView() {
        Map<String, String> rootAttr = new HashMap<>();
        rootAttr.put("class", "pageContentSide");
        Perch.create()
                .setHtmlUrl(url)
                .setRootTag("div")
                .setRootAttrs(rootAttr)
                .setViewContainer(mContainer)
                .addNodeViewAdapter(textNodeViewAdapter)
                .addNodeViewAdapter(imgNodeViewAdapter)
                .addNodeViewAdapter(newsHeadNodeViewAdapter)
                .setCallBack(new Perch.PerchCallBack() {
                    @Override
                    public void onSuccess() {
                        mSwipe.setRefreshing(false);
                        mSwipe.setEnabled(false);
                        renderNewsHeader();
                    }

                    @Override
                    public void onFail(String msg) {
                        mSwipe.setRefreshing(false);
                        mSwipe.setEnabled(false);
                    }
                })
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_btn:
                share();
                break;
            case R.id.up_btn:
                toTop();
                break;
            default:
                break;
        }
    }

    private void toTop() {
        mRootScroll.post(new Runnable() {
            @Override
            public void run() {
                mRootScroll.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    private void share() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);//设置分享行为
        shareIntent.setType("text/plain");//设置分享内容的类型
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "发现一条有趣的新闻");//添加分享内容标题
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);//添加分享内容
        //创建分享的Dialog
        shareIntent = Intent.createChooser(shareIntent, "分享");
        this.startActivity(shareIntent);
    }
}
