package com.example.arif.spinnerwithdatabase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arif on 13/10/16.
 */
public class ShowAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Information> data;
    Context context;
    public ShowAdapter(Context context,List<Information> data){
        this.data=data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information info=data.get(position);
        holder.nameSet.setText(info.getName());
        holder.subjectSet.setText(info.getSubject());
        holder.topicsSet.setText(info.getTopics());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView nameSet,subjectSet,topicsSet;

    public MyViewHolder(View itemView) {
        super(itemView);
        nameSet= (TextView) itemView.findViewById(R.id.name);
        subjectSet= (TextView) itemView.findViewById(R.id.subject);
        topicsSet= (TextView) itemView.findViewById(R.id.topics);
    }
}
