package com.example.beniten.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Beniten on 3/14/2018.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.Holder> {

    Context mContext;
    ArrayList<String> mArrayListString;

    public RecAdapter(Context mContext, ArrayList<String> mArrayListString) {
        this.mContext = mContext;
        this.mArrayListString = mArrayListString;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.mTextView.setText(mArrayListString.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrayListString.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public Holder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
