package com.mad.exercise6.activity;

import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mad.exercise6.R;
import com.mad.exercise6.model.Train;
import com.mad.exercise6.adapter.TrainAdapter;
import com.mad.exercise6.db.TrainDatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static List<Train> trains = new ArrayList<>();
    private RecyclerView mTrainRecyclerView;
    private TrainAdapter mTrainAdapter;
    public static TrainDatabaseHelper myDb;
    private final static int REQ_CODE = 1;

    private static final String PLATFORM = "PLATFORM";
    private static final String ARRIVAL_TIME = "ARRIVAL_TIME";
    private static final String ONTIMEORLATE = "ONTIMEORLATE";
    private static final String DESTINATION = "DESTINATION";
    private static final String DESTINATION_TIME = "DESTINATION_TIME";
    private static final String CANCEL = "CANCEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb = new TrainDatabaseHelper(this);
        trains = myDb.getData();
        trains.add(new Train("Albion Platform 1", new Random().nextInt(20) +"", "On Time", "Allawah", "14:11"));
        trains.add(new Train("Amcliffe Platform 2", new Random().nextInt(20) +"", "Late", "Central", "14:34"));
        trains.add(new Train("Artarmion Platform 3", new Random().nextInt(20) +"", "On Time", "Ahfield", "15:01"));
        trains.add(new Train("Berowra Platform 3", new Random().nextInt(20) +"", "Late", "Beverly", "15:18"));
        mTrainRecyclerView = findViewById(R.id.activity_main_train_recyclerview);
        mTrainAdapter = new TrainAdapter(this, trains);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTrainRecyclerView.setLayoutManager(mLayoutManager);
        mTrainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTrainRecyclerView.setAdapter(mTrainAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, REQ_CODE);
            }
        });
    }

    /**
     *  Handles Result from Activity 2
     *
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode == REQ_CODE) {
            Boolean cancel = intent.getBooleanExtra(CANCEL, true);
            if(cancel){
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show();
            } else {
                String platform = intent.getStringExtra(PLATFORM);
                String arrivalTime = intent.getStringExtra(ARRIVAL_TIME);
                String onTimeOrLate = intent.getStringExtra(ONTIMEORLATE);
                String destination = intent.getStringExtra(DESTINATION);
                String destinationTime = intent.getStringExtra(DESTINATION_TIME);

                myDb.insertData(platform, arrivalTime, onTimeOrLate, destination, destinationTime);

                trains = myDb.getData();
                Toast.makeText(this, "" + trains.size(), Toast.LENGTH_SHORT).show();
                mTrainAdapter = new TrainAdapter(this, trains);
                mTrainRecyclerView.setAdapter(mTrainAdapter);
                mTrainAdapter.notifyDataSetChanged();
            }

        }
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
                new SpinnerRefreshAsyncTask().execute();



            case R.id.action_delete:
                myDb.delete();
                trains = myDb.getData();

                mTrainAdapter = new TrainAdapter(this, trains);
                mTrainRecyclerView.setAdapter(mTrainAdapter);

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
            mTrainRecyclerView.setVisibility(View.INVISIBLE);
            pBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pBar.setVisibility(View.GONE);
            mTrainRecyclerView.setVisibility(View.VISIBLE);
            trains = myDb.getData();

            Toast.makeText(MainActivity.this, "" + trains.size(), Toast.LENGTH_SHORT).show();

            trains.add(new Train("Albion Platform 1", new Random().nextInt(20) +"", "On Time", "Allawah", "14:11"));
            trains.add(new Train("Amcliffe Platform 2", new Random().nextInt(20) +"", "Late", "Central", "14:34"));
            trains.add(new Train("Artarmion Plform 3", new Random().nextInt(20) +"", "On Time", "Ahfield", "15:01"));
            trains.add(new Train("Berowra Platform 3", new Random().nextInt(20) +"", "Late", "Beverly", "15:18"));
            mTrainAdapter.notifyDataSetChanged();
            mTrainAdapter = new TrainAdapter(MainActivity.this, trains);
            mTrainRecyclerView.setAdapter(mTrainAdapter);

    }
    }
}
