package ren.yueh.perchdemo.swustsc.nodeview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yuehuaren.BaseNodeHolder;
import com.yuehuaren.BaseNodeViewAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by yuehuaren on 2017/4/24.
 */

public class ImgNodeViewAdapter extends BaseNodeViewAdapter<BaseNodeHolder, ImageView> {
    public ImgNodeViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onAttachFinished() {
    }

    @Override
    protected ImageView attachView(Map nodeResult, final ImageView view) {
        final String url = nodeResult.get("src").toString();
        if (!TextUtils.isEmpty(url)) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Glide.with(context).load(url).into(view);
                }
            });
        }
        return view;
    }

    @Override
    protected ImageView onCreateViewModule(Context context) {
        return new ImageView(context);
    }

    @Override
    protected BaseNodeHolder onCreateNodeHolder() {
        return new ImageNodeHolder();
    }

    public static class ImageNodeHolder extends BaseNodeHolder {

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
}
