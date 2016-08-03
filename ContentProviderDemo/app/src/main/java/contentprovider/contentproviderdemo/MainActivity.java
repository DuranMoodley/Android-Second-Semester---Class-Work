package contentprovider.contentproviderdemo;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView callLogs;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        callLogs = (TextView) findViewById(R.id.txtCallLogs);

        Uri allCalls = Uri.parse("content://call_log/calls");
        Cursor c = getContentResolver().query(allCalls, null, null, null, null);

        int num = c.getColumnIndex(CallLog.Calls.NUMBER);
        int date = c.getColumnIndex(CallLog.Calls.DATE);
        int duration = c.getColumnIndex(CallLog.Calls.DURATION);

        String phNum = null;
        String callDate = null;
        String callDuration = null;
        String myCalls = "";
        Date callDayTime;

        while (c.moveToNext())
        {
            phNum = c.getString(num);
            callDate = c.getString(date);
            callDuration = c.getString(duration);
            callDayTime = new Date(Long.valueOf(callDate));
            myCalls +=  "Phone Number \t" + phNum + "\n" +
                        "Call Date : \t" + callDayTime + "\n" +
                        "Call Duration: \t" + callDuration + "\n\n";
        }

        c.close();
        callLogs.setText(myCalls);
    }
    //**********************************************************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //**********************************************************************8
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
