package com.rpifinal.hitema.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

public class GPSUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Location location = intent.getParcelableExtra(LocationManager.KEY_LOCATION_CHANGED);
    }
}
