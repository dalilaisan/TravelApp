package com.example.dalil.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ManageInterestsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ManageInterestsActivity";

    ImageView hospital, cafee, bank, drink, hotel, eat, shopping, transport;
    Button doneButtton;
    SharedPreferences sharedPreferences;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_interests);
        cafee = findViewById(R.id.caffee);
        hospital = findViewById(R.id.hospital);
        bank = findViewById(R.id.bank);
        drink = findViewById(R.id.drink);
        hotel = findViewById(R.id.hotel);
        eat = findViewById(R.id.eat);
        shopping = findViewById(R.id.shopping);
        transport = findViewById(R.id.transport);
//        doneButtton = findViewById(R.id.doneButton);

        cafee.setOnClickListener(this);
        hospital.setOnClickListener(this);
        bank.setOnClickListener(this);
        drink.setOnClickListener(this);
        hotel.setOnClickListener(this);
        eat.setOnClickListener(this);
        shopping.setOnClickListener(this);
        transport.setOnClickListener(this);
//        doneButtton.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.caffee:
               // cafee.setImageResource(R.drawable.cafe_checked_256px);
                if(!cafee.isActivated()) {
                 //add to interests
                     cafee.setActivated(true);
                    Log.d(TAG, "onClick: adding caffee to interests");
                    sharedPreferences.edit().putBoolean("caffee", true).apply();

                }
                else {
                    cafee.setActivated(false);
                 //delete from interests, if exists
                    Log.d(TAG, "onClick: not adding cafee to interests");
                    sharedPreferences.edit().putBoolean("caffee", false).apply();
                }
                break;
            case R.id.hospital:
                if(!hospital.isActivated())  {
                    //add to interests
                    hospital.setActivated(true);
                }
                else {
                    hospital.setActivated(false);
                    //delete from interests, if exists
                }
                break;
            case R.id.bank:
                if(!bank.isActivated()) {
                    //add to interests
                    bank.setActivated(true);

                }
                else {
                    bank.setActivated(false);
                    //delete from interests, if exists
                }
                break;
            case R.id.drink:
                if(!drink.isActivated()) {
                    //add to interests
                    drink.setActivated(true);

                }
                else {
                    drink.setActivated(false);
                    //delete from interests, if exists
                }
                break;
            case R.id.hotel:
                if(!hotel.isActivated()) {
                    //add to interests
                    hotel.setActivated(true);

                }
                else {
                    hotel.setActivated(false);
                    //delete from interests, if exists
                }
                break;
            case R.id.eat:
                if (!eat.isActivated()) {
                    //add to interests
                    eat.setActivated(true);

                }
                else {
                    eat.setActivated(false);
                    //delete from interests, if exists
                }
                break;
            case R.id.shopping:
                if(!shopping.isActivated()) {
                    //add to interests
                    shopping.setActivated(true);

                }
                else {
                    shopping.setActivated(false);
                    //delete from interests, if exists
                }
                break;
            case R.id.transport:
                if(!transport.isActivated())  {
                    //add to interests
                    transport.setActivated(true);

                }
                else {
                    transport.setActivated(false);
                    //delete from interests, if exists

                }
                break;
//            case R.id.doneButton:
//                break;
         }
        }

}
