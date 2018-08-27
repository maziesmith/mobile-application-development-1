package com.mad.exercise5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {
    private Context context;
    private List<Train> trains;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView arrivalTime, platform, status, destination_time, destination;

        public ViewHolder(View v) {
            super(v);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Train train = trains.get(position);
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
    }

    @Override   //Get the number of items in the train list
    public int getItemCount() {
        return trains.size();
    }

//    int getId(int position){
//        return data.get(position).ID;
//    }


}
