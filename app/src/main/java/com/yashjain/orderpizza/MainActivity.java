package com.yashjain.orderpizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.yashjain.orderpizza.adapters.cartAdapter;
import com.yashjain.orderpizza.adapters.pizzaAdapter;
import com.yashjain.orderpizza.adapters.removeItemAdapter;
import com.yashjain.orderpizza.data.MainAPI;
import com.yashjain.orderpizza.databinding.ActivityMainBinding;
import com.yashjain.orderpizza.model.PizzaDataList;
import com.yashjain.orderpizza.model.combinedPizzaList;
import com.yashjain.orderpizza.model.newPizza;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private  Call<PizzaDataList> list;
    private List<PizzaDataList> data_list;
    private pizzaAdapter adapter;
    public  static List <newPizza> newPizzas;
    ArrayList<combinedPizzaList> combinedPizzaLists;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data_list=new ArrayList<>();
        newPizzas=new ArrayList<>();
        //Fetching data from api
        getData();
        combinedPizzaLists=new ArrayList<>();
        binding.cart.setOnClickListener(view -> {
        if(newPizzas.size()>0){
            combinedPizzaLists.clear();
            for(int i=0;i<newPizzas.size();i++){
                combinedPizzaList c=new combinedPizzaList(1,newPizzas.get(i).getCrustName(),newPizzas.get(i).getSizeName());
                combinedPizzaLists.add(c);
            }

            for(int i=0;i<combinedPizzaLists.size();i++){
                for (int j=i;j<combinedPizzaLists.size()-1;j++){
                    if(combinedPizzaLists.get(i).getPizzaType()==combinedPizzaLists.get(j+1).getPizzaType() && combinedPizzaLists.get(i).getCrustType()==combinedPizzaLists.get(j+1).getCrustType()){
                        int num=combinedPizzaLists.get(i).getNumber();
                        ++num;
                        combinedPizzaLists.get(i).setNumber(num);
                        combinedPizzaLists.remove(j+1);
                    }
                }
            }
            open_dialog();
        }
        });
    binding.orderNow.setOnClickListener(view -> {
        if(newPizzas.size()>0){
            data_list.clear();
            finish();
            startActivity(getIntent());
            Toast.makeText(this, "Order successfully placed!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please select pizza first!!", Toast.LENGTH_SHORT).show();
        }
    });
    }

    private void open_dialog() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.cart_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
        cartAdapter cartAdapter=new cartAdapter(this,combinedPizzaLists);
        RecyclerView recyclerView=dialog.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
    }

    private void getData(){
        list= MainAPI.get().getPizzaList();
        list.enqueue(new Callback<PizzaDataList>() {
            @Override
            public void onResponse(Call<PizzaDataList> call, Response<PizzaDataList> response) {
                PizzaDataList list=response.body();
                data_list.add(list);
                //Setting up adapter
                adapter=new pizzaAdapter(MainActivity.this,data_list);
                binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.recyclerView.setAdapter(adapter);
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d("MainApiCall", "onResponse: "+data_list.get(0).getName());
            }

            @Override
            public void onFailure(Call<PizzaDataList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                Log.d("MainApiCall", ""+t);

            }
        });

    }



}