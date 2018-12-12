package com.example.indalamar.scp_1488;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavouriteOnesAdapter
        extends RecyclerView.Adapter<FavouriteOnesAdapter.ViewHolder> {

    private final FavouriteOnesList mParentActivity;
    private final List<RecyclerViewItem> mValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerViewItem item = (RecyclerViewItem) view.getTag();
            TextView id = (TextView) view.findViewById(R.id.id_text);
            String id_text = (String) id.getText();
            Context context = view.getContext();
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.putExtra("id", id_text);
            intent.putExtra("url", item.imageUrl);
            intent.putExtra("text", item.text);
            intent.putExtra("owner_id", item.id);
            context.startActivity(intent);
        }
    };

    FavouriteOnesAdapter(FavouriteOnesList parent,
                         List<RecyclerViewItem> items) {
        mValues = items;
        mParentActivity = parent;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    public void updateData() {
            notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(mOnClickListener);
        holder.mIdView.setText(Integer.toString(position));
        holder.mContentView.setText(mValues.get(position).text);
        holder.itemView.setTag(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}