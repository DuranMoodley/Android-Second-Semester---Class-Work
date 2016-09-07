package attendance.student.prjstudentattendancerecord;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

public class GeofenceTransitionIntentService extends IntentService
{
    public GeofenceTransitionIntentService()
    {
        super("GeofenceTransitionIS");
    }
    //*********************************************************
    @Override
    protected void onHandleIntent(Intent intent)
    {
        String transitionType;
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if(geofencingEvent.hasError()){
            Log.e("OnHandleIntent","" + geofencingEvent.getErrorCode());
        }
        else
        {
            int transition = geofencingEvent.getGeofenceTransition();

            if(transition == Geofence.GEOFENCE_TRANSITION_ENTER)
            {
                transitionType = "You are in your Lecture Venue. Enjoy !!!";
                sendNotification(transitionType);
            }
            else if(transition == Geofence.GEOFENCE_TRANSITION_EXIT)
            {
                transitionType = "You have left your Lecture Venue. Please Return to your venue to Check Out.";
                sendNotification(transitionType);
            }
        }
    }
    //******************************************************************************************
    private void sendNotification(String notificationDescription)
    {
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this);
        builder.setColor(Color.BLUE)
                .setContentTitle("Student Attendance")
                .setContentText(notificationDescription)
                .setAutoCancel(true)
                .setVibrate(new long[] { 200, 200, 600, 600, 600, 200, 200,})
                .setSmallIcon(R.mipmap.ic_checkin);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}
