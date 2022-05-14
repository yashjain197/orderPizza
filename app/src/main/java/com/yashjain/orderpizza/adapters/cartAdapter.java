package com.yashjain.orderpizza.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashjain.orderpizza.R;
import com.yashjain.orderpizza.model.combinedPizzaList;

import java.util.List;

public class cartAdapter extends  RecyclerView.Adapter<cartAdapter.ViewHolder> {

    private Context context;
    private List<combinedPizzaList> list;

    public cartAdapter(Context  context,List<combinedPizzaList> list){
        this.context=context;
        this.list=list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cart_layout,parent,false);
        return new cartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        combinedPizzaList c=list.get(position);

        holder.name.setText(""+c.getNumber()+" "+c.getPizzaType()+" "+c.getCrustType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.pizzaName);
        }
    }
}
