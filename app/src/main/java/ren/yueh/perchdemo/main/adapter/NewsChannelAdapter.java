package ren.yueh.perchdemo.main.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ren.yueh.perchdemo.R;

/**
 * Created by yueh on 2017/5/10.
 */

public class NewsChannelAdapter extends RecyclerView.Adapter<NewsChannelAdapter.ItemViewHolder> implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mItemClickListener;
    private List<Channel> channels;
    private Context context;

    public NewsChannelAdapter(Context context, List<Channel> channels) {
        this.channels = channels;
        this.context = context;
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_channel_item, parent, false);
        view.setOnClickListener(this);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Channel channel = channels.get(position);
        holder.icon.setImageResource(channel.iconId);
        holder.name.setText(channel.name);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return channels == null ? 0 : channels.size();
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(view, (Integer) (view.getTag()));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.channel_icon);
            name = (TextView) itemView.findViewById(R.id.channel_name);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, int position);
    }

    public static class Channel {
        public int iconId;
        public String name;

        public Channel(int iconId, String name) {
            this.iconId = iconId;
            this.name = name;
        }
    }
}
