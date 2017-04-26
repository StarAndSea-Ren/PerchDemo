package ren.yueh.perchdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ren.yueh.perchdemo.R;

/**
 * Created by yuehuaren on 2017/4/24.
 */

public class NewsListViewAdapter extends RecyclerView.Adapter<NewsListViewAdapter.ItemViewHolder> implements View.OnClickListener {
    private List<Map<String, String>> mData;
    private Context mContext;
    private OnRecyclerViewItemClickListener mItemClickListener;

    public NewsListViewAdapter(Context context, List<Map<String, String>> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public NewsListViewAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_news_list_item, parent, false);
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsListViewAdapter.ItemViewHolder holder, int position) {
        Map<String, String> item = mData.get(position);
        holder.titleTxt.setText(item.get("title"));
        holder.dateTxt.setText(item.get("date"));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, (Integer) (v.getTag()));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTxt;
        public TextView dateTxt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleTxt = (TextView) itemView.findViewById(R.id.news_title);
            dateTxt = (TextView) itemView.findViewById(R.id.news_date);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }
}
