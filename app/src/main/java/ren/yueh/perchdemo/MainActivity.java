package ren.yueh.perchdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {

//    Map<String, String> rootAttr = new HashMap<>();
//    rootAttr.put("id", "NewsPostDetailContent");
//    Perch.create()
//            .setHtmlUrl("http://www.cs.swust.edu.cn/news/872/70/.html")
//    .setRootTag("div")
//    .setRootAttrs(rootAttr)
//    .setViewContainer(container)
//    .addNodeViewAdapter(new TextNodeViewAdapter(getApplication()))
//            .addNodeViewAdapter(new ImgNodeViewAdapter(getApplication()))
//            .start();

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new NewsListPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
