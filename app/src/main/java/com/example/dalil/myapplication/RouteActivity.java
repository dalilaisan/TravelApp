package com.example.dalil.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class RouteActivity extends AppCompatActivity {
    private static final String TAG = "RouteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: route activity created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);


//        clicking on the floating action button will let the user add a new place to the route
//        FloatingActionButton floatingActionButton = findViewById(R.id.);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RouteActivity.this,);
//                startActivity(intent);
//            }
//        });

    }







}
