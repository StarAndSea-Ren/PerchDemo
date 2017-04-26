package ren.yueh.perchdemo.nodeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodeViewAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ren.yueh.perchdemo.R;

/**
 * Created by yuehuaren on 2017/4/21.
 */

public class TextNodeViewAdapter extends BaseNodeViewAdapter<BaseNodeHolder, TextView> {

    public TextNodeViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView attachView(Map<String, String> nodeResult, TextView view) {
        view.setText(nodeResult.get(BaseNodeHolder.TEXT_KEY));
        return view;
    }

    @Override
    protected TextView onCreateViewModule(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.text_view_module, null, false);
        return textView;
    }

    @Override
    protected BaseNodeHolder onCreateNodeHolder() {
        BaseNodeHolder result = new TextNodeHolder();
        return result;
    }

    public static class TextNodeHolder extends BaseNodeHolder {

        @Override
        protected String createTag() {
            return "p";
        }

        @Override
        protected Map<String, String> createKnownAttrs() {
            return null;
        }

        @Override
        protected List<String> createUnKnownAttrKeys() {
            return Arrays.asList(BaseNodeHolder.TEXT_KEY);
        }
    }
}
