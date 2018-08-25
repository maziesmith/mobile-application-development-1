package com.mad.exercise2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import org.w3c.dom.Text;

public class ActivityTwo extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PHONETYPE = "phoneType";
    public static final String AGREEORNOT = "AgreeOrNot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        /**
         *  Get the passed values from the intent
         */

        Intent anotherIntent = getIntent();
        String name = anotherIntent.getStringExtra(NAME);
        String email = anotherIntent.getStringExtra(EMAIL);
        String phone = anotherIntent.getStringExtra(PHONE);
        String phoneT = anotherIntent.getStringExtra(PHONETYPE);


        /**
         *  Set those passed values in the Activity two TextViews
         */

        TextView activity2_name = findViewById(R.id.activity2_name);
        if(name.equals("")){
            activity2_name.setText(getString(R.string.your_name));
        } else {
            activity2_name.setText(name);
        }

        TextView activity2_email = findViewById(R.id.activity2_email);
        if(email.equals("")){
            activity2_email.setText(getString(R.string.your_email));

        } else {
            activity2_email.setText(email);
        }

        TextView activity2_phone =findViewById(R.id.activity2_phone);
        if(phone.equals("")){
            activity2_phone.setText(getString(R.string.your_phone));
        } else {
            activity2_phone.setText(phone);
        }


        TextView activity2_phoneType = findViewById(R.id.activity2_phoneType);
        if(phoneT.equals("")){
            activity2_phoneType.setText(getString(R.string.phone_prompt));

        } else {
            activity2_phoneType.setText(phoneT);
        }

    }
    /**
     *  Submit button click listener that is responsible for sending back Agree or disagree result
     */

    public void onActivity2SubmitButtonCLick(View view) {
        CheckBox myCheck = (CheckBox)findViewById(R.id.activity2_agree);
        Boolean agree =  myCheck.isChecked();
        Intent intent = new Intent();
        intent.putExtra(AGREEORNOT, agree);
        setResult(RESULT_OK, intent);
        finish();
    }
}
