package com.example.dalil.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ManageInterestsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView hospital, cafee, bank, drink, hotel, eat, shopping, transport;
    Button doneButtton;

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
        doneButtton = findViewById(R.id.doneButton);

        cafee.setOnClickListener(this);
        hospital.setOnClickListener(this);
        bank.setOnClickListener(this);
        drink.setOnClickListener(this);
        hotel.setOnClickListener(this);
        eat.setOnClickListener(this);
        shopping.setOnClickListener(this);
        transport.setOnClickListener(this);
        doneButtton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.caffee:
                cafee.setImageResource(R.drawable.cafe_checked_256px);
                if(cafee.isSelected()) {
                 //add to interests

                }
                else {
                 //delete from interests, if exists

                }
                break;
            case R.id.hospital:
                hospital.setImageResource(R.drawable.hospital_checked_256px);
                //add to interests
                break;
            case R.id.bank:
                bank.setImageResource(R.drawable.bank_checked_256px);
                //add to interests
                break;
            case R.id.drink:
                drink.setImageResource(R.drawable.bar_checked_256px);
                //add to interests
                break;
            case R.id.hotel:
                hotel.setImageResource(R.drawable.hotel_checked_256px);
                //add to interests
                break;
            case R.id.eat:
                eat.setImageResource(R.drawable.restaurant_checked_256px);
                //add to interests
                break;
            case R.id.shopping:
                shopping.setImageResource(R.drawable.shopping_checked_256px);
                //add to interests
                break;
            case R.id.transport:
                transport.setImageResource(R.drawable.transport_checked_256px);
                //add to interests
                break;
            case R.id.doneButton:
                break;


        }
        }
}
