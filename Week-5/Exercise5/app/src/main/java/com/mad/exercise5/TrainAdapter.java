package com.mad.exercise5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {
    private Context context;
    private List<Train> trains;


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView arrivalTime, platform, status, destination_time, destination;
        public ProgressBar progressBar;
        public LinearLayout greenLl;
        public ViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progress_bar_small);
            greenLl = v.findViewById(R.id.first);
            arrivalTime = v.findViewById(R.id.arrival_time);
            platform = v.findViewById(R.id.platform);
            destination = v.findViewById(R.id.destination);
            destination_time = v.findViewById(R.id.destination_time);
            status = v.findViewById(R.id.status);
        }
    }



    public TrainAdapter(Context context, List<Train> trains) {
        this.trains=trains;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Train train = trains.get(position);
        holder.destination_time.setText(train.getDestinationTime());
        holder.destination.setText(train.getDestination());
        holder.platform.setText(train.getPlatform());
        if(train.getStatus().equals("Late")){
            holder.status.setTextColor(Color.RED);
            holder.status.setText(train.getStatus());
        } else {
            holder.status.setText(train.getStatus());

        }

        holder.status.setText(train.getStatus());

        holder.arrivalTime.setText(train.getArrivalTime());

        holder.greenLl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context,context.getString(R.string.refresh) + train.getDestination(), Toast.LENGTH_SHORT).show();
//                holder.progressBar.setVisibility(View.VISIBLE);
                new SpinnerRefreshGreenAsyncTask(holder,position).execute();
//                holder.progressBar.setVisibility(View.INVISIBLE);


            }

        });

    }




    @Override   //Get the number of items in the train list
    public int getItemCount() {
        return trains.size();
    }

//    int getId(int position){
//        return data.get(position).ID;
//    }


    public class SpinnerRefreshGreenAsyncTask extends AsyncTask<Void, Void, Void> {


        ViewHolder holder;
        int position;
        public SpinnerRefreshGreenAsyncTask(ViewHolder holder, int position) {
            this.position = position;
            this.holder = holder;
            
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPreExecute() {

            holder.arrivalTime.setVisibility(View.INVISIBLE);
            holder.progressBar.setVisibility(View.VISIBLE);
            

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            holder.progressBar.setVisibility(View.INVISIBLE);
            final Train train = trains.get(position);
            Random x = new Random(10);


            holder.arrivalTime.setText(String.valueOf(x));
        }

    }


}
