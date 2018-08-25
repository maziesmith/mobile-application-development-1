package com.mad.exercise3;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * MainActivity class - Main Class of the App
 */

public class MainActivity extends AppCompatActivity {
    final boolean[] a = {true};
    public static final String FIRSTNAME = "FIRSTNAME";
    public static final String LASTNAME = "LASTNAME";
    public static final String PHONENUMBER = "PHONENUMBER";
    public static final String IMAGE = "IMAGE";
    public static final String EMAIL = "EMAIL";
    public static final String MAD = "MAD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText  firstName = findViewById(R.id.fname);

        EditText lastName = findViewById(R.id.lname);
        EditText phoneNumber = findViewById(R.id.phone);
        final ImageView image = findViewById(R.id.image);

        EditText email = findViewById(R.id.email);
        Button mSwap = findViewById(R.id.swap);


        if(savedInstanceState !=  null ){
            firstName.setText(savedInstanceState.getCharSequence(FIRSTNAME));
            lastName.setText(savedInstanceState.getCharSequence(LASTNAME));
            phoneNumber.setText(savedInstanceState.getCharSequence(PHONENUMBER));
            //image.setImageResource(savedInstanceState.getInt(IMAGE));
            email.setText(savedInstanceState.getCharSequence(EMAIL));
        }

        /**
         * Email Edit text focus listner
         */
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    Toast.makeText(MainActivity.this, R.string.email_focused, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, R.string.email_notFocused, Toast.LENGTH_SHORT).show();

                }
            }
        });

        /**
         * Swap button handler
         */
        mSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(a[0]){
                    //image.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.another));

                    image.setImageResource(R.drawable.another);
                    image.setTag(R.drawable.android);
                    a[0] = false;
                } else
            }
        });

    }

    /**
     * Rotate Button handler
     */
    public void rotate(View view) {
        int currentOrientation = this.getResources().getConfiguration().orientation;
        //Toast.makeText(this, "" + currentOrientation, Toast.LENGTH_SHORT).show();
        if( currentOrientation  == 1){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }

    }
    /**
     * onSaveInstanceState method saves all the the EditText in UI
     */

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        EditText firstName = findViewById(R.id.fname);
        EditText lastName = findViewById(R.id.lname);
        EditText phone = findViewById(R.id.phone);
        EditText email = findViewById(R.id.email);

//        Drawable image = getResources().getDrawable(R.drawable.android);
//
//        Drawable aImage = getResources().getDrawable(R.drawable.another);

        ImageView image = findViewById(R.id.image);


        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String phoneNo = phone.getText().toString();
        String emailAd = email.getText().toString();


        Integer imgResources = (Integer)image.getTag();


        outState.putString(FIRSTNAME, fName);
        outState.putString(LASTNAME, lName);
        outState.putString(PHONENUMBER, phoneNo);
        outState.putString(EMAIL, emailAd);
        outState.putInt(IMAGE, imgResources);

        super.onSaveInstanceState(outState, outPersistentState);

        Log.d(MAD, getString(R.string.saveInstance_invoked));

    }
    /**
     * onRestoreInstanceState method restores all the the EditText in UI
     */

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

//        EditText firstName = findViewById(R.id.fname);
//        EditText lastName = findViewById(R.id.lname);
//        EditText phoneNumber = findViewById(R.id.phone);
//        EditText email = findViewById(R.id.email);
//        ImageView image = findViewById(R.id.image);
//
//
//        firstName.setText(savedInstanceState.getCharSequence("FIRSTNAME"));
//        lastName.setText(savedInstanceState.getCharSequence("LASTNAME"));
//        phoneNumber.setText(savedInstanceState.getCharSequence("PHONENUMBER"));
//        image.setImageResource(savedInstanceState.getInt("IMAGE"));
//        email.setText(savedInstanceState.getCharSequence("EMAIL"));

        Log.d(MAD, getString(R.string.restore_invoked));

    }
}
