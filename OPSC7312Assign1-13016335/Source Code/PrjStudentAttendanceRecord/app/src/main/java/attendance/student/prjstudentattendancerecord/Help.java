/*
Help.java
Displays help information to the student
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/17/16
 */
package attendance.student.prjstudentattendancerecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView helpTv = (TextView) findViewById(R.id.tvHelpText);
        FactoryClass objFactory = new FactoryClass(this);
        assert helpTv != null;
        helpTv.setText(objFactory.readDate("help"));
    }
}
