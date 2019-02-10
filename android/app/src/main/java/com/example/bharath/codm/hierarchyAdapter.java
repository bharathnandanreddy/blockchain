package com.example.bharath.codm;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import org.web3j.abi.datatypes.Address;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.util.ArrayList;
import java.util.List;


public class hierarchyAdapter extends RecyclerView.Adapter<hierarchyAdapter.RecyclerViewHolders>{

    private ArrayList<Tuple2<String, String>> itemList;
    private Context context;

    public hierarchyAdapter(Context context) {
        this.context = context;


    }
    public void setItems(ArrayList<Tuple2<String, String>> items) {
        Log.e("hhv","inserted"+items.size());
        itemList = items;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        final RecyclerViewHolders mainHolder = (RecyclerViewHolders) holder;// holder
        Log.e("hhv","binding");
        Tuple2<String, String> product=itemList.get(position);
        mainHolder.name.setText(product.getValue1());
        TextView type=mainHolder.type;

        if(position==(itemList.size()-1)){
            type.setText("Owner");
        }

        else if(position==0){
            type.setText("Manufacturer");
        }


        else {
            type.setText("Product holder");
        }






    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hierarchy, viewGroup, false);



        return new RecyclerViewHolders(itemView);

    }


    class RecyclerViewHolders extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView type;




        public RecyclerViewHolders(View view) {
            super(view);
            // Find all views ids

            name=(TextView)view.findViewById(R.id.name);
            type=(TextView)view.findViewById(R.id.types);




        }


    }
}
