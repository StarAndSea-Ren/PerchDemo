package ren.yueh.perchdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.yuehuaren.Perch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ren.yueh.perchdemo.adapter.NewsListViewAdapter;
import ren.yueh.perchdemo.nodeview.ImgNodeViewAdapter;
import ren.yueh.perchdemo.nodeview.NewsListNodeViewAdapter;
import ren.yueh.perchdemo.nodeview.TextNodeViewAdapter;

public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private String mNewsListUrl;
    private FrameLayout mFrameLayout;
    private SwipeRefreshLayout mSwipe;
    private NewsListNodeViewAdapter adapter;

    public NewsListFragment() {
        // Required empty public constructor
    }


    public static NewsListFragment newInstance(String newsListUrl) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString("news_list_url", newsListUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsListUrl = getArguments().get("news_list_url").toString();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mSwipe.setOnRefreshListener(this);
        mSwipe.setRefreshing(true);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.container);
        adapter = new NewsListNodeViewAdapter(getContext());
        adapter.setParentActivity(getActivity());
        getNewsListView();
        return view;
    }

    @Override
    public void onRefresh() {
        getNewsListView();
    }

    private void getNewsListView() {
        Map<String, String> rootAttr = new HashMap<>();
        rootAttr.put("class", "pageContentSide");
        Perch.create()
                .setHtmlUrl(mNewsListUrl)
                .setRootTag("div")
                .setRootAttrs(rootAttr)
                .setViewContainer(mFrameLayout)
                .setNodeListViewAdapter(adapter)
                .setCallBack(new Perch.PerchCallBack() {
                    @Override
                    public void onSuccess() {
                        mSwipe.setRefreshing(false);
                    }

                    @Override
                    public void onFail(String msg) {
                        mSwipe.setRefreshing(false);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }
}
