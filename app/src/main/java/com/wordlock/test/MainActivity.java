package com.wordlock.test;
import android.app.*; import android.os.*; import android.content.*; import android.net.Uri; import android.provider.Settings; import android.widget.*;
public class MainActivity extends Activity {
public void onCreate(Bundle b){super.onCreate(b); LinearLayout l=new LinearLayout(this); l.setOrientation(1); l.setPadding(40,60,40,40); TextView t=new TextView(this); t.setText("WordLock v0.1\nKilit ekranı testi"); t.setTextSize(24); l.addView(t);
Button p=new Button(this); p.setText("Ekran üzerinde gösterme izni"); p.setOnClickListener(v->startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:"+getPackageName())))); l.addView(p);
Button s=new Button(this); s.setText("Testi başlat"); s.setOnClickListener(v->{if(Settings.canDrawOverlays(this)){startForegroundService(new Intent(this,WordService.class)); Toast.makeText(this,"Ekranı kilitle ve uyandır",1).show();}else Toast.makeText(this,"Önce izin ver",0).show();}); l.addView(s);
Button q=new Button(this); q.setText("Testi durdur"); q.setOnClickListener(v->stopService(new Intent(this,WordService.class))); l.addView(q); setContentView(l);}
}