package com.mad.exercise2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PHONETYPE = "phoneType";
    public static final String AGREEORNOT = "AgreeOrNot";
    public String MSG = "Lifecycle :";

    private static final int REQ_CODE = 1234 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button exit = findViewById(R.id.exit);
        Button clear = findViewById(R.id.clear);
        Button submit = findViewById(R.id.submit);

        final EditText name = (EditText) findViewById(R.id.activity1_name);
        final EditText email = (EditText) findViewById(R.id.activity1_email);
        final EditText phone = (EditText) findViewById(R.id.activity1_phone);
        final Spinner spinner = (Spinner)findViewById(R.id.activity1_spinner);

        /**
         *  Clear Button Listener
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                email.setText("");
                phone.setText("");
                spinner.setSelection(0);
            }
        });

        /**
         *  Exit Button Listener
         */

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Exiting soon. . . . ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
                finish();
                onPause();
                onStop();
                onDestroy();
            }


        });

        /**
         *  Submit Button Listener
         */
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Submit button created", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                String realName = name.getText().toString();
                String realEmail = email.getText().toString();
                String realPhone = phone.getText().toString();
                String text = spinner.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this, ActivityTwo.class);
                intent.putExtra(NAME,"" + realName);
                intent.putExtra(EMAIL,"" + realEmail);
                intent.putExtra(PHONE,"" + realPhone);
                intent.putExtra(PHONETYPE,"" + text);
                startActivityForResult(intent, REQ_CODE);
            }
        });

    }


    /**
     *  Handles Result from Activity 2 and shows Snackbar
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQ_CODE) {

            // came back from Activity2
            Boolean data = intent.getBooleanExtra(AGREEORNOT, true);
            LinearLayout linear;
            linear = findViewById(R.id.linear);
            if(data){
                Snackbar.make(linear, getString(R.string.agree), Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(linear, getString(R.string.disagree), Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    /**
     *  Adding all the Activity LifeCycle methods to MainActivity
     *  and adding a Log.d(...) statement to each lifecycle method.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MSG,"onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MSG,"onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MSG,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MSG,"onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MSG,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MSG,"onDestroy invoked");
    }
}


