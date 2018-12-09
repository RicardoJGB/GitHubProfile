package com.mobileapps.githubprofile.controller;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapps.githubprofile.ItemAdapter;
import com.mobileapps.githubprofile.R;
import com.mobileapps.githubprofile.api.Client;
import com.mobileapps.githubprofile.api.Service;
import com.mobileapps.githubprofile.model.Item;
import com.mobileapps.githubprofile.model.ItemResponse;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView Disconnected;
    private Item item;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeContainer =(SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJson();
                Toast.makeText(MainActivity.this,"GitHub Users Refreshed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews (){
        pd= new ProgressDialog(this);
        pd.setMessage("Fetching User...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJson();
    }

    private void loadJson(){
        Disconnected = (TextView) findViewById(R.id.tvDisconnected);
        try{
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            retrofit2.Call<ItemResponse> call = apiService.getItem();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(retrofit2.Call<ItemResponse> call, Response<ItemResponse> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(),items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(retrofit2.Call<ItemResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(MainActivity.this,"Error Fetching!",Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error",e.getMessage());
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
