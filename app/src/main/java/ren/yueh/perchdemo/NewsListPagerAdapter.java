package ren.yueh.perchdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        int color = 0xffff0000;
        switch (position) {
            case 0:
                color = 0xffff0000;
                break;
            case 1:
                color = 0xff00ff00;
                break;
            case 2:
                color = 0xff0000ff;
                break;
            case 3:
                color = 0xff336699;
                break;
            default:
                break;
        }
        return NewsListFragment.newInstance(color);
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
