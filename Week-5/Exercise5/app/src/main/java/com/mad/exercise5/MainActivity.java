package com.mad.exercise5;

import android.app.ProgressDialog;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Train> trains = new ArrayList<>();
    private RecyclerView mTrainRecyclerView;
    private TrainAdapter mTrainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trains.add(new Train("Berowra Platform 3", "12 \nmins", "Late", "Beverly", "15:18"));
                mTrainAdapter.notifyDataSetChanged();
                initRecycler();
            }
        });

    }

    private void initData() {
            trains.add(new Train("Albion Platform 1", new Random().nextInt(20) +" \nmins", "On Time", "Allawah", "14:11"));
            trains.add(new Train("Amcliffe Platform 2", new Random().nextInt(20) +" \nmins", "Late", "Central", "14:34"));
            trains.add(new Train("Artarmion Platform 3", new Random().nextInt(20) +" \nmins", "On Time", "Ahfield", "15:01"));
            trains.add(new Train("Berowra Platform 3", new Random().nextInt(20) +" \nmins", "Late", "Beverly", "15:18"));
            initRecycler();
    }

    private void initRecycler() {
        mTrainRecyclerView = findViewById(R.id.activity_main_train_recyclerview);
        mTrainAdapter = new TrainAdapter(this, trains);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTrainRecyclerView.setLayoutManager(mLayoutManager);
        mTrainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTrainRecyclerView.setAdapter(mTrainAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_refresh:
//                return true;
                new SpinnerRefreshAsyncTask().execute();

            case R.id.action_delete:
//                return true;
                trains.clear();
                initRecycler();
            case R.id.action_quit:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
        Spinner Refresh Async Task to refresh all recycler view's Arrival time
     */

    private class SpinnerRefreshAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgressBar pBar = findViewById(R.id.pbar);
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {
//            mTrainRecyclerView.setVisibility(View.INVISIBLE);
            pBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            pBar.setVisibility(View.GONE);
            mTrainRecyclerView.setVisibility(View.VISIBLE);
            initData();
            initRecycler();
        }
    }



}
