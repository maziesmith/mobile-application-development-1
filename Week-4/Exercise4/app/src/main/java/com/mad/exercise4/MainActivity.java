package com.mad.exercise4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button mJokeOne;
    private Button mJokeThree;
    public static TextView mJokeView;
    private ProgressDialog mDialog;
    private ProgressDialog mDialogForDownloading;
    private Spinner mSpinner;
    String mSelectedSpinnerText = "";
    String mMainUrl;

    /*
     * onCreate Lifecycle method will handle initialization and have spinner select handler
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJokeOne = findViewById(R.id.joke1);
        mJokeThree = findViewById(R.id.joke3);
        mJokeView = findViewById(R.id.joke);
        mSpinner = findViewById(R.id.spinner);

        /*
         * Spinner Item select Handler
         */
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedSpinnerText = mSpinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this, "" + mSelectedSpinnerText + " jokes selected", Toast.LENGTH_SHORT).show();
                if (mSelectedSpinnerText.equals("Long")) {
                    mMainUrl = getString(R.string.longurl);
                } else if (mSelectedSpinnerText.equals("Medium")) {
                    mMainUrl = getString(R.string.mediumUrl);
                } else {
                    mMainUrl = getString(R.string.shorturl);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        /*
         * Joke 1 click handler
         */
        mJokeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Download1JokeAsyncTask().execute();
            }
        });

        /*
         * Joke 3 click handler
         */
        mJokeThree.setOnClickListener(new View.OnClickListener() {
            private int numJokes = 3;

            @Override
            public void onClick(View view) {
                new DownloadNJokeAsyncTask(numJokes).execute();
                TextView tv = findViewById(R.id.joke);
                tv.setMovementMethod(new ScrollingMovementMethod());
            }
        });
    }

    /*
     * AsyncTask class for Joke 1 Button
     */
    public class Download1JokeAsyncTask extends AsyncTask<Void, Void, String> {

        String joke;
        private String error;

        /*
         * doInBackground will run in seperate thread and will handle fetching data from URL
         */
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(mMainUrl);
                URLConnection conn = url.openConnection();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                joke = bufferReader.readLine();
                bufferReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                error = getString(R.string.failedtodownload);
            } catch (IOException e) {
                e.printStackTrace();
                error = getString(R.string.failedtodownload);
            }
            return null;

        }

        /*
         * onPostExecute will run after completely the task on doInBackground()
         */
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (error != null) {
                MainActivity.mJokeView.setText(this.error);
                mDialog.dismiss();
            } else {
                MainActivity.mJokeView.setText(this.joke);
                Toast.makeText(MainActivity.this, "" + mMainUrl, Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }

        }

        /*
         * onPreExecute will run before executing doInBackground()
         */
        @Override
        protected void onPreExecute() {
            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage(getString(R.string.downloading));
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDialog.show();
        }
    }


    /*
     * AsyncTask class for Joke 3 Button
     */
    public class DownloadNJokeAsyncTask extends AsyncTask<Void, Integer, String[]> {

        String[] jokes;
        private String error;
        int numJokes;
        int count = 0;


        /*
         * Constructor for DownloadNJokeAsyncTask class
         */
        public DownloadNJokeAsyncTask(int numJokes) {
            this.numJokes = numJokes;
            jokes = new String[this.numJokes];
            mDialogForDownloading = new ProgressDialog(MainActivity.this);
            mDialogForDownloading.setMax(3);
            mDialogForDownloading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }

        /*
         * doInBackground will run in seperate thread and will handle fetching data from URL
         */
        @Override
        protected String[] doInBackground(Void... voids) {
            try {
                int i = 0;
                int count = 0;
                do {
                    URL url = new URL(mMainUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    conn.setRequestMethod("GET");
                    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    jokes[i] = bufferReader.readLine();
                    count++;
                    publishProgress(count);
                    bufferReader.close();
                    i++;
                } while (i < numJokes);

            } catch (IOException e) {
                e.printStackTrace();
                error = getString(R.string.failed_to_download2);
            }
            return null;

        }


        /*
         * onPostExecute handles the display of jokes in TextView
         */
        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if (error != null) {
                MainActivity.mJokeView.setText(this.error);
                mDialogForDownloading.dismiss();
            } else {
                MainActivity.mJokeView.setText(jokes[0] + "\n\n");
                MainActivity.mJokeView.append(jokes[1] + "\n\n");
                MainActivity.mJokeView.append(jokes[2] + "\n\n");
                mDialogForDownloading.dismiss();
            }

        }

        /*
         * onPreExecute handle the popup of dialog bar
         */
        @Override
        protected void onPreExecute() {
            mDialogForDownloading.setMessage(getString(R.string.downloading) + count + getString(R.string.dots));
            mDialogForDownloading.show();
        }

        /*
         * onProgressUpdate handles the increment of number of jokes downloaded
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mDialogForDownloading.setMessage(getString(R.string.downloading) + values[0] + getString(R.string.dots));
            mDialogForDownloading.setProgress(values[0]);
        }

    }


}