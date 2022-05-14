package com.yashjain.orderpizza.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashjain.orderpizza.MainActivity;
import com.yashjain.orderpizza.R;
import com.yashjain.orderpizza.model.newPizza;

import org.w3c.dom.Text;

import java.util.List;

public class removeItemAdapter extends RecyclerView.Adapter<removeItemAdapter.ViewHolder> {

    private List<newPizza> pizzaList;
    private Context context;
    pizzaAdapter.ViewHolder holder1;

    public removeItemAdapter(Context context, List<newPizza> pizzaList, pizzaAdapter.ViewHolder holder1){
        this.pizzaList=pizzaList;
        this.context=context;
        this.holder1=holder1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.remove_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        newPizza pizza=pizzaList.get(position);

        holder.name.setText(pizza.getCrustName());
        holder.crustType.setText(pizza.getSizeName());
        holder.price.setText("₹"+pizza.getPrice());

        holder.deleteBtn.setOnClickListener(view -> {
            pizzaList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, pizzaList.size());
            TextView cartNo=((MainActivity) context).findViewById(R.id.cartNumber);
            cartNo.setText(""+pizzaList.size());
            if(pizzaList.size()<=0)
            cartNo.setVisibility(View.GONE);
            int p=0;
            for(int i=0;i<pizzaList.size();i++){
                p+=pizzaList.get(i).getPrice();
            }
            //Setting cost in recycler view
            TextView cost=((MainActivity) context).findViewById(R.id.price);
            cost.setText("₹"+p);
            holder1.number.setText(""+pizzaList.size());


        });

    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,price,crustType;
        ImageView deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.Pizza_name);
            price=itemView.findViewById(R.id.price);
            crustType=itemView.findViewById(R.id.crust);
            deleteBtn=itemView.findViewById(R.id.delete_button);

        }
    }

}
