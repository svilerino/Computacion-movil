package com.cmovil.pathfinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GPSBackgroundServiceLauncher extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent startServiceIntent = new Intent(context, GPSBackgroundService.class);
        context.startService(startServiceIntent);
	}

}
