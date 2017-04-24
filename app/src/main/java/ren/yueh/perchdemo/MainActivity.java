package ren.yueh.perchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuehuaren.Perch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button testBtn;
    private FrameLayout container;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBtn = (Button) findViewById(R.id.test_btn);
        container = (FrameLayout) findViewById(R.id.container);
        img = (ImageView) findViewById(R.id.img);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> rootAttr = new HashMap<>();
                rootAttr.put("id", "NewsPostDetailContent");
                Perch.create()
                        .setHtmlUrl("http://www.cs.swust.edu.cn/news/872/70/.html")
                        .setRootTag("div")
                        .setRootAttrs(rootAttr)
                        .setViewContainer(container)
                        .addNodeViewAdapter(new TextNodeViewAdapter(getApplication()))
                        .addNodeViewAdapter(new ImgNodeViewAdapter(getApplication()))
                        .start();
            }
        });
    }
}
