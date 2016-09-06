/*
StudentMain.java
Displays the sliding tab layout consisting of 2 fragements
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */
package attendance.student.prjstudentattendancerecord;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class StudentMain extends AppCompatActivity {

    private final CharSequence[] tabTitles  = {"Check In","Report"};
    private SharedPreferences myprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytool_bar);
        setSupportActionBar(toolbar);

        myprefs = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        //Creating the Viewpager and parsing fragment manager
        int totalTabs = 2;
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), tabTitles, totalTabs);

        //Assigning view pager view and setting the adapter
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
        assert viewPager != null;
        viewPager.setAdapter(viewPageAdapter);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tbSlidingTab);
        assert slidingTabLayout != null;
        slidingTabLayout.setDistributeEvenly(true);

        slidingTabLayout.setViewPager(viewPager);
    }
    //***********************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //***********************************************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else if(id == R.id.map)
        {
            Intent newActivity = new Intent(StudentMain.this,StudentMap.class);
            startActivity(newActivity);
        }

        return super.onOptionsItemSelected(item);
    }
    //***********************************************************
    @Override
    protected void onStop() {
        super.onStop();
        if (myprefs.getBoolean("Check In", false))
        {
            setNotification();
        }
    }
    //***********************************************************************
    public void setNotification()
    {
        //Set Notifcation Properties
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this);
        notifyBuilder.setSmallIcon(R.mipmap.ic_checkin);
        notifyBuilder.setTicker("Tap to Return to App !!");
        notifyBuilder.setContentTitle("Student Attendance");
        notifyBuilder.setContentText("Please Check Out when Lecture has Ended.");
        notifyBuilder.setAutoCancel(true);
        notifyBuilder.setVibrate(new long[] { 200, 200, 600, 600, 600, 200, 200,});
        notifyBuilder.setLights(Color.RED,50,50);

        //Create back stack for the activity after back button clicked
        //Adds the intent back to the top of the stack
        Intent lauchActivity = new Intent(StudentMain.this, StudentMain.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(StudentMain.class);
        stackBuilder.addNextIntent(lauchActivity);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notifyBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notifyBuilder.build());
    }
    //***********************************************************************
}
