package ren.yueh.perchdemo.swustsc.nodeview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodesViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/26.
 */

public class NewsHeadNodeViewAdapter extends BaseNodesViewAdapter<BaseNodeHolder, View> {
    private List<Map<String, String>> mData = new ArrayList<>();

    public NewsHeadNodeViewAdapter(Context context) {
        super(context);
    }

    public List<Map<String, String>> getData() {
        return mData;
    }

    @Override
    protected View attachView(Map<String, String> nodeResult, View view) {
        mData.add(nodeResult);
        return null;
    }

    @Override
    protected List<BaseNodeHolder> onCreateNodeHolders() {
        return Arrays.asList(new NewsTitleNode(), new NewsTimeNode(), new NewsTypeNode(), new NewsReadNode());
    }

    @Override
    protected View onCreateViewModule(Context context) {
        return new TextView(context);
    }

    public static class NewsTitleNode extends BaseNodeHolder {
        public NewsTitleNode() {
            super();
        }
        @Override
        protected String createTag() {
            return "h2";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String, String> attr = new HashMap<>();
            attr.put("class", "contentTitle");
            return attr;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY, "class");
        }
    }

    public static class NewsTimeNode extends BaseNodeHolder {
        public NewsTimeNode() {
            super();
        }
        @Override
        protected String createTag() {
            return "div";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String, String> attr = new HashMap<>();
            attr.put("class", "postDate");
            return attr;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY, "class");
        }
    }

    public static class NewsTypeNode extends BaseNodeHolder {
        public NewsTypeNode() {
            super();
        }

        @Override
        protected String createTag() {
            return "div";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String, String> attr = new HashMap<>();
            attr.put("class", "postCategory");
            return attr;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY, "class");
        }
    }

    public static class NewsReadNode extends BaseNodeHolder {
        public NewsReadNode() {
            super();
        }
        @Override
        protected String createTag() {
            return "div";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            Map<String, String> attr = new HashMap<>();
            attr.put("class", "postviews");
            return attr;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(TEXT_KEY, "class");
        }
    }
}
