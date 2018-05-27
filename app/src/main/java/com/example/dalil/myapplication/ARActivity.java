package com.example.dalil.myapplication;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import eu.kudan.kudan.ARAPIKey;
import eu.kudan.kudan.ARArbiTrack;
import eu.kudan.kudan.ARGyroPlaceManager;
import eu.kudan.kudan.ARImageNode;


public class ARActivity extends eu.kudan.kudan.ARActivity implements GestureDetector.OnGestureListener {
    private static final String TAG = "ARActivity";
    private GestureDetectorCompat gestureDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Comment this out for the time being unless you plan to create UI elements
        //   setContentView(R.layout.activity_ar);

        ARAPIKey key = ARAPIKey.getInstance();
        key.setAPIKey("agWZcpYLYjBxCbWf2qZx6k+PWISqeGtFCqKaZwYtwS+kdn1HKiQAmsJ55STRBe9BqCw3VwG6qL+ESI5ntTF/iV/uekLG3PCokaUE0/uTzqhaYlxRdmuNBIduzBCjq3mV2na+gy3ffHH9Ipc7eIN0geTj3p+ppsmK0U399iGmN38ndIh6k2y16cByWIecMSU3yw3Ztw7gHRqf83hVhZ5T2ACGK4SNkQhhdKp+CTaR5W3amYCJBgwumqFqNFyI9UniuMk70T/cQObRQum2U51OjjbMfmEAwIBt8Q8jD2yACzye6K4/1O4pZhbGEbiDeLrAfxqMwBAe5o6vnYIilGNnpDhfi3wOHhRaqtLOVvB58GUIFTnAPvmYFVnLWRJmCUZ9FJNDyX3ALCl/alFEWh+A/a6NFjcwLGKI9drPuGG4ONFg4p0l+p3b9DZoLzszlmWAflI/UFzQa++kQn3/sclO9i0vPnpi0LWoABm5vGswLVAIX/0k6384GXxfkADI6fjGtf62XJ5ImaVDiiREa9mabWEQGoifghQG1sGNDYgBIYEpiaLsVzOfTALpe20Q7kFCMjedJImQhhuLtEK1BXfXJEed1QqUOsG9IeKxKk28GbOtOF9w3yrSF3gnJslzZxF2kEF3C6ckog8byagS+4p37FJmbpPsiKNH1Qm0LuouGcQ=");
        // Create gesture recogniser to start and stop arbitrack
        gestureDetect = new GestureDetectorCompat(this,this);
    }

    @Override
    public void setup() {
        super.setup();

        // Initialise ArbiTrack.
        Log.d(TAG, "setup: artbitrach initialized");
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();
        arbiTrack.initialise();

        // Initialise gyro placement.
        Log.d(TAG, "setup: gyro placement initialized");
        ARGyroPlaceManager gyroPlaceManager = ARGyroPlaceManager.getInstance();
        gyroPlaceManager.initialise();

        //Setting up the target node:

        // Create a node to be used as the target.
        ARImageNode targetNode = new ARImageNode("Cow Target.png");

        // Add it to the Gyro Placement Manager's world so that it moves with the device's Gyroscope.
        gyroPlaceManager.getWorld().addChild(targetNode);

        // Rotate and scale the node to ensure it is displayed correctly.
        targetNode.rotateByDegrees(90.0f, 1.0f, 0.0f, 0.0f);
        targetNode.rotateByDegrees(180.0f, 0.0f, 1.0f, 0.0f);

        targetNode.scaleByUniform(0.3f);

        // Set the ArbiTracker's target node.
        arbiTrack.setTargetNode(targetNode);


        //Setup content with ArbiTrack:

        // Create a node to be tracked.
        ARImageNode trackingNode = new ARImageNode("Cow Tracking.png");

        // Rotate the node to ensure it is displayed correctly.
        trackingNode.rotateByDegrees(90.0f, 1.0f, 0.0f, 0.0f);
        trackingNode.rotateByDegrees(180.0f, 0.0f, 1.0f, 0.0f);

        // Add the node as a child of the ArbiTracker's world.
        arbiTrack.getWorld().addChild(trackingNode);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: touch event detected");
        gestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }


    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: tap up detected!");
        ARArbiTrack arbiTrack = ARArbiTrack.getInstance();

        // If arbitrack is tracking, stop tracking so that its world is no longer rendered, and make the target node visible.
        if (arbiTrack.getIsTracking())
        {
            arbiTrack.stop();
            Log.d(TAG, "onSingleTapUp: arbi track stopped");
            arbiTrack.getTargetNode().setVisible(true);
        }

        // If it's not tracking, start tracking and hide the target node.
        else
        {
            arbiTrack.start();
            Log.d(TAG, "onSingleTapUp: arbi track started");
            arbiTrack.getTargetNode().setVisible(false);
        }

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
