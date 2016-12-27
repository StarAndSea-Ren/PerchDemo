package ren.yueh.perchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.List;

import ren.yueh.perch.OnDisplayableListener;
import ren.yueh.perch.Perch;
import ren.yueh.perch.Relation;
import ren.yueh.perch.RelationsAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Perch.create().setHtmlUrl("http://ued.ctrip.com/blog/how-browsers-work-rendering-engine-html-parsing-series-ii.html").setRelationsAdapter(new RelationsAdapter() {
                    @Override
                    public List<Relation> genRelations() {
                        return null;
                    }

                    @Override
                    public Relation getRelation(int position) {
                        return null;
                    }

                    @Override
                    public int getCount() {
                        return 0;
                    }
                }).setOnDisplayableListener(new OnDisplayableListener() {
                    @Override
                    public void OnDisplayable(ViewGroup viewGroup) {

                    }
                }).start();
            }
        }).start();

    }
}
