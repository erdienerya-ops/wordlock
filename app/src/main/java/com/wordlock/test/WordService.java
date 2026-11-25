package com.wordlock.test;
import android.app.*;import android.content.*;import android.os.*;
public class WordService extends Service {
BroadcastReceiver receiver=new BroadcastReceiver(){public void onReceive(Context c,Intent i){if(Intent.ACTION_SCREEN_ON.equals(i.getAction())){android.content.SharedPreferences p=WordWidget.prefs(c);long[] intervals={0,300000,1800000,3600000,86400000};if(System.currentTimeMillis()-p.getLong("last",0)>=intervals[p.getInt("interval",0)])WordWidget.refresh(c,true);}}};
public void onCreate(){super.onCreate();NotificationManager n=getSystemService(NotificationManager.class);n.createNotificationChannel(new NotificationChannel("wordlock","WordLock",2));startForeground(1,new Notification.Builder(this,"wordlock").setSmallIcon(android.R.drawable.ic_menu_info_details).setContentTitle("WordLock kelime güncellemesi etkin").build());registerReceiver(receiver,new IntentFilter(Intent.ACTION_SCREEN_ON),Context.RECEIVER_NOT_EXPORTED);}
public int onStartCommand(Intent i,int f,int s){return START_STICKY;}public void onDestroy(){unregisterReceiver(receiver);super.onDestroy();}public IBinder onBind(Intent i){return null;}
}