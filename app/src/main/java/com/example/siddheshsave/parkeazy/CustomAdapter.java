package com.example.siddheshsave.parkeazy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private List<Mall> mallList;
    Button button;

    public CustomAdapter(View view, Context context, List<Mall> mallList) {
        this.context = context;
        this.mallList = mallList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.rowlayout,null,true);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Mall mall = mallList.get(position);
        holder.name.setText(mall.getName());
        holder.location.setText(mall.getLocation());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfinityMallAndheri.class);
                intent.putExtra("Name",mall.getName());
                intent.putExtra("Location",mall.getLocation());
                context.startActivity(intent);
            }
        });
        //holder.image.setImageDrawable(context.getResources().getDrawable(mall.getImage()));
        //Glide.with(context).load(mall.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mallList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,location;
        ImageView image;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            location=itemView.findViewById(R.id.location);
            image=itemView.findViewById(R.id.image);
            button=itemView.findViewById(R.id.button_card);
        }
    }
}

