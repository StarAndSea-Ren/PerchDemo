package ren.yueh.perchdemo.baijia.nodeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodesListViewAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yueh on 2017/5/11.
 */

public class NewsListNodeViewAdapter extends BaseNodesListViewAdapter<BaseNodeHolder, RecyclerView> {

    public NewsListNodeViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected List<BaseNodeHolder> onCreateNodeHolders() {
        return null;
    }

    @Override
    protected RecyclerView onCreateViewModule(Context context) {
        return null;
    }

    @Override
    protected void bindListViewData(List<Map<String, String>> nodeResultsData, RecyclerView recyclerView) {

    }

    public static class TitleImgNodeHolder extends BaseNodeHolder{

        @Override
        protected String createTag() {
            return "img";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            return null;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList("src");
        }
    }

    public static class TitleTxtNodeHolder extends BaseNodeHolder{

        @Override
        protected String createTag() {
            return "a";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String,String> attrs = new HashMap<>();
            attrs.put("target","_blank");
            return attrs;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY,"href","mon");
        }
    }
    public static class SummaryNodeHolder extends BaseNodeHolder{

        @Override
        protected String createTag() {
            return "p";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String,String> attrs = new HashMap<>();
            attrs.put("class","feeds-item-text1");
            return attrs;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY);
        }
    }
    public static class AuthorNodeHolder extends BaseNodeHolder{

        @Override
        protected String createTag() {
            return "a";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String,String> attrs = new HashMap<>();
            attrs.put("class","feeds-item-author");
            attrs.put("target","_blank");
            return attrs;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY);
        }
    }
    public static class DateNodeHolder extends BaseNodeHolder{

        @Override
        protected String createTag() {
            return "span";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String,String> attrs = new HashMap<>();
            attrs.put("class","tm");
            return attrs;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY);
        }
    }

}
