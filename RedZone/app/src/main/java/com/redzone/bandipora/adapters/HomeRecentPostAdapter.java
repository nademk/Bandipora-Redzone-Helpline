package com.redzone.bandipora.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.redzone.bandipora.R;
import com.redzone.bandipora.listeners.ListItemClickListener;
import com.redzone.bandipora.models.Posts;

import java.util.ArrayList;

public class HomeRecentPostAdapter extends RecyclerView.Adapter<HomeRecentPostAdapter.ViewHolder> {

    private Context mContext;
    private Activity mActivity;

    private ArrayList<Posts> mContentList;
    private ListItemClickListener mItemClickListener;

    public HomeRecentPostAdapter(Context mContext, Activity mActivity, ArrayList<Posts> mContentList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mContentList = mContentList;
    }

    public void setItemClickListener(ListItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intent, parent, false);
        return new ViewHolder(view, viewType, mItemClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private CardView lyout;
        private CardView btnCall;
        private ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            tvTitle = (TextView) itemView.findViewById(R.id.tv1);
            lyout = (CardView) itemView.findViewById(R.id.lyt_container);
            btnCall = (CardView) itemView.findViewById(R.id.call);
            btnCall.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(getLayoutPosition(), view);
            }
        }
    }

    @Override
    public int getItemCount() {
        return (null != mContentList ? mContentList.size() : 0);

    }

    @Override
    public void onBindViewHolder(HomeRecentPostAdapter.ViewHolder mainHolder, int position) {
        final Posts model = mContentList.get(position);

        mainHolder.tvTitle.setText(Html.fromHtml(model.getName()));
    }
}