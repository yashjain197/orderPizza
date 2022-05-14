package com.yashjain.orderpizza.adapters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.yashjain.orderpizza.MainActivity;
import com.yashjain.orderpizza.R;
import com.yashjain.orderpizza.model.Crust;
import com.yashjain.orderpizza.model.PizzaDataList;
import com.yashjain.orderpizza.model.Size;
import com.yashjain.orderpizza.model.newPizza;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class pizzaAdapter extends RecyclerView.Adapter<pizzaAdapter.ViewHolder> {
    private Context context;
    private List<PizzaDataList> list;
    private List<Crust> crust_list;
    private List<Size> crust_size_list;
    private TextView totalPizza;
    List<newPizza> newList;

    public pizzaAdapter(Context context,List<PizzaDataList> list){
    this.context=context;
    this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pizza_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PizzaDataList data=list.get(position);
        Boolean foodType=data.getIsVeg();
        final int[] pizza_number = {0};
        totalPizza=holder.number;
        holder.pizzaName.setText(data.getName());
        if(data.getDescription()!=null){
            holder.description.setText(data.getDescription());
        }

        newList=new ArrayList<>();


        if(foodType) holder.food_type.setImageResource(R.drawable.veg);
        else holder.food_type.setImageResource(R.drawable.non_veg);

        holder.increaseBtn.setOnClickListener(view -> {
         show_Dialog(holder);
        });

        holder.decreaseBtn.setOnClickListener(view -> {
            if(pizza_number[0]>0){
                --pizza_number[0];
                holder.number.setText(""+pizza_number[0]);
            }
        });
        crust_list=data.getCrusts();

        holder.decreaseBtn.setOnClickListener(view -> {
            if(newList.size()>0) {
                showRemoveDialog(holder);
                holder.number.setText(""+newList.size());
            }
        });

    }

    //Dialog for add pizza
    private void show_Dialog(ViewHolder holder) {
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_pizza);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
        ArrayList<String> crustType=new ArrayList<>();
        Button cancel,save;
        cancel=dialog.findViewById(R.id.cancel_button);
        save=dialog.findViewById(R.id.saveBtn);

        final String[] itemSelected = {null};
        final String[] sizeName = {null};
        final int[] crustId = new int[1];
        final int[] sizeId = new int[1];
        final int[] price = new int[1];

        for(int i=0;i<crust_list.size();i++){
            crustType.add(crust_list.get(i).getName());
            Log.d("MainT", "show_Dialog: "+crust_list.get(i).getName());
        }
        ArrayList<String> sizeOfPizza=new ArrayList<>();
        Spinner crustSpinner=dialog.findViewById(R.id.crustSpinner);
        Spinner sizeSpinner=dialog.findViewById(R.id.sizeSpinner);


        //Setting spinner adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(context,  android.R.layout.simple_spinner_dropdown_item, crustType);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        crustSpinner.setAdapter(adapter);
        crustSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelected[0] =adapterView.getItemAtPosition(i).toString();
                for(int j=0;j<crust_list.size();j++){
                    if(crust_list.get(j).getName().equals(itemSelected[0])){
                        setSizeAdapter(crust_list.get(j).getSizes());
                        crustId[0] = crust_list.get(i).getId();
                        break;
                    }
                }
            }

            private void setSizeAdapter(List<Size> list) {
                crust_size_list=list;
                ArrayList<String> size=new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    size.add(list.get(i).getName());
                }
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(context,  android.R.layout.simple_spinner_dropdown_item, size);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                sizeSpinner.setAdapter(adapter);
                sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        sizeName[0]=adapterView.getItemAtPosition(i).toString();

                        for (i=0;i<crust_size_list.size();i++){
                            if(crust_size_list.get(i).getName().equals(sizeName[0])){
                                sizeId[0]=crust_size_list.get(i).getId();
                                price[0]=crust_size_list.get(i).getPrice();
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cancel.setOnClickListener(view -> {
            dialog.dismiss();
        });

        save.setOnClickListener(view -> {
            newPizza newPizza=new newPizza(crustId[0],sizeId[0],price[0],itemSelected[0],sizeName[0]);
            MainActivity.newPizzas.add(newPizza);
            newList= MainActivity.newPizzas;
            if(context instanceof MainActivity) {
                TextView cartNo=((MainActivity) context).findViewById(R.id.cartNumber);
                cartNo.setText(""+newList.size());

                cartNo.setVisibility(View.VISIBLE);
                int p=0;
                for(int i=0;i<newList.size();i++){
                    p+=newList.get(i).getPrice();
                }
                Log.d("PricePizza", "show_Dialog: "+p);
                TextView cost=((MainActivity) context).findViewById(R.id.price);
                cost.setText("₹"+p);
                holder.number.setText(""+newList.size());
            }
            dialog.dismiss();
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        MaterialButton increaseBtn,decreaseBtn;
        TextView number,pizzaName,description;
        ImageView food_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            increaseBtn=itemView.findViewById(R.id.increase);
            decreaseBtn=itemView.findViewById(R.id.decrease);
            number=itemView.findViewById(R.id.integer_number);
            pizzaName=itemView.findViewById(R.id.pizzaName);
            description=itemView.findViewById(R.id.description);
            food_type=itemView.findViewById(R.id.type_food);
        }
    }

    private void showRemoveDialog(ViewHolder holder){
        Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.item_remove);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
        removeItemAdapter removeItemAdapter=new removeItemAdapter(context,newList,holder);
        RecyclerView recyclerView=dialog.findViewById(R.id.recyclerViewRemove);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(removeItemAdapter);
    }
}
