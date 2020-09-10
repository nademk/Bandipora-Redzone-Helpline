package com.redzone.bandipora.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.redzone.bandipora.R;
import com.redzone.bandipora.listeners.ListItemClickListener;
import com.redzone.bandipora.models.Categories;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private Context mContext;
    private Activity mActivity;

    private ArrayList<Categories> mCategoryList;
    private ListItemClickListener mItemClickListener;

    public CategoryAdapter(Context mContext, Activity mActivity, ArrayList<Categories> mCategoryList) {
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mCategoryList = mCategoryList;
    }

    public void setItemClickListener(ListItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view, viewType, mItemClickListener);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView categoryImg;
        private TextView categoryTitle;
        private CardView lytContainer;
        private ListItemClickListener itemClickListener;


        public ViewHolder(View itemView, int viewType, ListItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;
            categoryTitle = (TextView) itemView.findViewById(R.id.tv1);
            lytContainer = (CardView) itemView.findViewById(R.id.lyt_container);

            lytContainer.setOnClickListener(this);

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
        return (null != mCategoryList ? mCategoryList.size() : 0);

    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder mainHolder, int position) {
        final Categories model = mCategoryList.get(position);
        mainHolder.categoryTitle.setText(Html.fromHtml(model.getCategoryName()));
    }
}