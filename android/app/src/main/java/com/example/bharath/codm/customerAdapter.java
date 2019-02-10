package com.example.bharath.codm;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.web3j.abi.datatypes.Address;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.util.ArrayList;
import java.util.List;


public class customerAdapter extends RecyclerView.Adapter{

    private ArrayList<Tuple4<String,String,String,List<Address>>> itemList = new ArrayList<>();
    private Context context;


    protected boolean showLoader;
    private static final int VIEWTYPE_ITEM = 1;
    private static final int VIEWTYPE_LOADER = 2;



    public customerAdapter(Context context) {


        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.e("mdf","on crate");

        if (viewType == VIEWTYPE_LOADER) {

            // Your Loader XML view here
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loader_item_layout, parent, false);
            // Your LoaderViewHolder class
            return new LoadingViewHolder(view);



        } else if (viewType == VIEWTYPE_ITEM) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_details, parent,false);
            RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
            return rcv;
        }



        throw new IllegalArgumentException("Invalid ViewType: " + viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("binding","binding");
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loaderViewHolder = (LoadingViewHolder)holder;
            if (showLoader) {

                loaderViewHolder.mProgressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.mProgressBar.setVisibility(View.GONE);
            }

            return;
        }

        ((RecyclerViewHolders)holder).bindView(position);

    }



    @Override
    public int getItemCount() {
        if (itemList == null || itemList.size() == 0) {
            return 0;
        }
        return this.itemList.size()+1;
    }


    @Override
    public long getItemId(int position) {
        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {

            // id of loader is considered as -1 here
            return -1;
        }
        return super.getItemId(position);
    }



    @Override
    public int getItemViewType(int position) {

        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {
            return VIEWTYPE_LOADER;
        }

        return VIEWTYPE_ITEM;
    }


    public void showLoading(boolean status) {
        showLoader = status;
    }

    public void setItems(ArrayList<Tuple4<String,String,String,List<Address>>> items) {
        Log.e("hhv","inserted");
        itemList = items;

    }



    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar mProgressBar;

        public LoadingViewHolder(View view) {
            super(view);
            mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }


    class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener   {

        public TextView pname,company;

        public RecyclerViewHolders(View itemView) {

            super(itemView);

            Log.e("mdf","recyclerVirew");
            itemView.setOnClickListener(this);

            pname=(TextView)itemView.findViewById(R.id.pname);

            company=(TextView)itemView.findViewById(R.id.company);
        }


        public void bindView(final int position){


            pname.setText(itemList.get(position).getValue2());
            company.setText(itemList.get(position).getValue3());

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, itemList.get(getPosition()).getValue2(), Toast.LENGTH_SHORT).show();

            Intent i=new Intent(context,Prodetails.class);
            i.putExtra("id",itemList.get(getPosition()).getValue1());

            context.startActivity(i);
        }



    }
}
