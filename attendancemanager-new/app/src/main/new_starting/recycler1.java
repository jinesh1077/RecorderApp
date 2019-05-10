package com.example.android.new_starting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by shubhamgupta on 27/01/19.
 */

public class recycler1 extends RecyclerView.Adapter<recycler1.m2> {

    private String[] data;
    public recycler1(String[] data)
    {
    this.data=data;
    }
    @Override
    public m2 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater in1=LayoutInflater.from(parent.getContext());
        View view =in1.inflate(R.layout.list_item, parent ,false);
        return new m2(view);
    }

    @Override
    public void onBindViewHolder(m2 holder, int position) {
    String title=data[position];
    holder.t1.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class m2 extends RecyclerView.ViewHolder {
        RadioGroup g1;
        TextView t1;
        public m2(View itemView) {
            super(itemView);
            g1=(RadioGroup) itemView.findViewById(R.id.constant1);
            t1=(TextView) itemView.findViewById(R.id.sub1);
        }
    }
}
