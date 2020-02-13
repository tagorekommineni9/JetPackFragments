package com.example.jetpackfragments;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapater extends RecyclerView.Adapter<RecycleAdapater.ViewHolder> {

    private ArrayList<Pokemon_> arrypro;
    private Context context;
    private View.OnClickListener itemlistener;

    public RecycleAdapater(ArrayList<Pokemon_> arrypro, Context context) {
        this.arrypro = arrypro;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(arrypro.get(position).getImage()).into(holder.pkimg);
        holder.pkname.setText(arrypro.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrypro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView pkimg;
        TextView pkname;

        public ViewHolder(View itemView){
            super(itemView);

            pkimg = itemView.findViewById(R.id.img_pk);
            pkname = itemView.findViewById(R.id.txt_pknm);

            itemView.setTag(this);
        }
    }
}
