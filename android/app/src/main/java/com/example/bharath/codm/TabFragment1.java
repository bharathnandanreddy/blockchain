package com.example.bharath.codm;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;


import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TabFragment1 extends Fragment {

    private static final boolean GRID_LAYOUT = false;
    private static final int ITEM_COUNT = 100;
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(100000);
    private static final BigInteger GAS_LIMIT=BigInteger.valueOf(2000000) ;
    private customerAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Tuple4<String, String, String, List<Address>>> products=new ArrayList<>();

    NestedScrollView mScrollView;
    public static TabFragment1 newInstance() {
        return new TabFragment1();
    }
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

                mScrollView=(NestedScrollView)rootView.findViewById(R.id.scrollView);


        List<Address> l=new ArrayList<>();
        Tuple4<String, String, String, List<Address>> t=new Tuple4<>("rdxcgv","sdf","zsdx",l);

        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();

        recyclerView=rootView.findViewById(R.id.recycler_view);
         adapter=new customerAdapter(getActivity());

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setItems(products);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance

        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";
            try  {
                Web3j web3j = Web3jFactory.build(new HttpService("http://192.168.43.127:8545"));

                String account=web3j.ethAccounts().sendAsync().get().getAccounts().get(contract.acc);
                Credentials credentials = Credentials.create(account);
                Log.e("valid :",account);
                Producting contract=Producting.load(com.example.bharath.codm.contract.contract,web3j,credentials, new BigInteger("240000"), new BigInteger("2400000"));
                Log.e("valid :",String.valueOf(contract.isValid()));
                Log.e("jdrygc",String.valueOf("customers.size()"));
                List customers = contract.getCustomerProducts(account).send();
                Log.e("jdrygc",String.valueOf(customers.size()));
                for(Object customer:customers){
                    Tuple4<String, String, String, List<Address>> product=contract.getProductDetails(customer.toString()).send();
                    products.add(product);
                    List<Address> l=new ArrayList<>();
                    Tuple4<String, String, String, List<Address>> t=new Tuple4<>("rdxcgv","sdf","zsdx",l);
                }
                Log.e("size",String.valueOf(products.get(0).getValue1()));


            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {


            // dismiss the progress dialog after receiving data from API
            Collections.reverse(products);
            adapter.setItems(products);
            adapter.notifyDataSetChanged();


        }

    }
}