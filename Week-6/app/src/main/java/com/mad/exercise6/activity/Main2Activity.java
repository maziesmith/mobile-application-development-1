package com.mad.exercise6.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mad.exercise6.R;

public class Main2Activity extends AppCompatActivity {

    private static final String PLATFORM = "PLATFORM";
    private static final String ARRIVAL_TIME = "ARRIVAL_TIME";
    private static final String ONTIMEORLATE = "ONTIMEORLATE";
    private static final String DESTINATION = "DESTINATION";
    private static final String DESTINATION_TIME = "DESTINATION_TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void addButtonHandler(View view) {
        EditText platformET = findViewById(R.id.platform);
        String platform = platformET.getText().toString();

        EditText arrivalET = findViewById(R.id.arrival_time);
        String arrival_time = arrivalET.getText().toString();

        Spinner timeSpinner = findViewById(R.id.on_time_spinner);
        String onTimeOrLate = timeSpinner.getSelectedItem().toString();

        EditText destinationET = findViewById(R.id.destination);
        String destination = destinationET.getText().toString();

        EditText destinationTimeET = findViewById(R.id.destination_time);
        String destinationTime = destinationTimeET.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(PLATFORM, platform);
        intent.putExtra(ARRIVAL_TIME, arrival_time);
        intent.putExtra(ONTIMEORLATE, onTimeOrLate);
        intent.putExtra(DESTINATION, destination);
        intent.putExtra(DESTINATION_TIME, destinationTime);
        intent.putExtra("CANCEL", false);

        Toast.makeText(this, R.string.added, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelButtonHandler(View view) {
        Intent intent = new Intent();
        intent.putExtra("CANCEL", true);
        setResult(RESULT_OK, intent);
        finish();
    }
}
