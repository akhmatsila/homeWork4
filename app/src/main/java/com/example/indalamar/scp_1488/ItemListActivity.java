package com.example.indalamar.scp_1488;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListActivity extends AppCompatActivity {

    private ArrayList<RecyclerViewItem> items = new ArrayList<>();

    private static ModelApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        final View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);



        api = RetrofitEngine.getApi();

        api.getMyJSON().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                for (int i = 0; i < response.body().getGlobalItem().getItems().size(); ++i) {
                    items.add(new RecyclerViewItem(response.body().getGlobalItem().getItems().get(i)));
                }
                ((RecyclerView) recyclerView).getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
            }
        });
    }

    public void transToFavouriteOnes(View view) {
        Context context = view.getContext();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(context, FavouriteOnesList.class);
        context.startActivity(intent);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, items));
    }

}
