package com.himanshu.a2zlearning.rankings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshu.a2zlearning.R;

import java.util.List;

public class rankAdapter extends RecyclerView.Adapter<rankAdapter.viewHolder> {

    private Context context;
    private List<basiclist> rankList;

    rankAdapter(Context context, List<basiclist> rankList) {
        this.context = context;
        this.rankList = rankList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranksingle,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        basiclist item = rankList.get(position);
        holder.rankName.setText(item.getName());
        holder.rankNum.setText(""+item.getMarks());
    }

    @Override
    public int getItemCount() {
        return rankList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        TextView rankName;
        TextView rankNum;
        viewHolder(@NonNull View itemView) {
            super(itemView);
            rankName = itemView.findViewById(R.id.rankName);
            rankNum = itemView.findViewById(R.id.rankNum);
        }
    }
}