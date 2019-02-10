package com.example.bharath.codm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {
    private  String id;
    private hierarchyAdapter adapter;
    private Tuple4<String, String, String, List<Address>> product;
    private Tuple2<String,String> owner;
    private ArrayList<Tuple2<String,String>> product_holders=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rview);
        adapter=new hierarchyAdapter(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Details.this);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setItems(product_holders);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        id =(getIntent().getStringExtra("id"));
        Details.MyAsyncTasks myAsyncTasks = new Details.MyAsyncTasks();
        myAsyncTasks.execute();

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
                final Producting contract=Producting.load(com.example.bharath.codm.contract.contract,web3j,credentials, new BigInteger("240000"), new BigInteger("2400000"));
                Log.e("valid :",String.valueOf(contract.isValid()));

                  product = contract.getProductDetails(id).send();
                Log.e("jdrygc",product.getValue2());

                List adds=product.getValue4();
                for(Object add:adds){

                    Tuple2<String,String> o=contract.getOwner(add.toString()).send();

                    product_holders.add(o);

                    Log.e("tag", String.valueOf(product_holders.size()));
                }





            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {


            // dismiss the progress dialog after receiving data from API

           TextView pname=(TextView)findViewById(R.id.pname);
           pname.setText(product.getValue2());

            TextView company=(TextView)findViewById(R.id.company);
            company.setText(product.getValue3());




            if(product_holders.size()>0) {
                TextView name=(TextView)findViewById(R.id.owner);
                name.setText(product_holders.get(product_holders.size() - 1).getValue1());


                TextView contact = (TextView) findViewById(R.id.contact);

                contact.setText(product_holders.get(product_holders.size() - 1).getValue2());




            }

            adapter.setItems(product_holders);
            adapter.notifyDataSetChanged();




        }

    }
}
