package ren.yueh.perchdemo.swustsc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ren.yueh.perchdemo.swustsc.NewsListFragment;

/**
 * Created by yuehuaren on 2017/4/24.
 */

public class NewsListPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 4;

    public NewsListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        String newsListUrl = "";
        switch (position) {
            case 0:
                newsListUrl = "http://www.cs.swust.edu.cn/recent-news/school-news.html";
                break;
            case 1:
                newsListUrl = "http://www.cs.swust.edu.cn/students/students-news.html";
                break;
            case 2:
                newsListUrl = "http://www.cs.swust.edu.cn/recent-news/school-events.html";
                break;
            case 3:
                newsListUrl = "http://www.cs.swust.edu.cn/recent-news/Academic-events.html";
                break;
            default:
                break;
        }
        return NewsListFragment.newInstance(newsListUrl);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "学院动态";
            case 1:
                return "学生动态";
            case 2:
                return "通知公告";
            case 3:
                return "学术动态";
            default:
                return "";
        }
    }
}
