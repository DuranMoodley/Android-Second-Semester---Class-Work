package demo.services.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //************************************************************************
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplication(),"Service created",Toast.LENGTH_SHORT).show();
    }
    //************************************************************************
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplication(),"Service Started\nHello " + intent.getStringExtra("name") ,Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }
    //************************************************************************
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplication(),"Service Stopped " ,Toast.LENGTH_SHORT).show();
    }
}
