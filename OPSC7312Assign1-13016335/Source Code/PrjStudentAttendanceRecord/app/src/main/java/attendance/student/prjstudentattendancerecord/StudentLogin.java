/*
StudentLogin.java
Allows the student to register their course and student number
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */
package attendance.student.prjstudentattendancerecord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class StudentLogin extends AppCompatActivity {

    private EditText studentNumberedt;
    private EditText studentNameedt;
    private SharedPreferences myprefs;
    private EditText courseItemet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studentNumberedt = (EditText) findViewById(R.id.edtStudentNumber);
        courseItemet = (EditText) findViewById(R.id.edtCourse);
        studentNameedt = (EditText) findViewById(R.id.edtStudname);
        myprefs = this.getSharedPreferences("myPreference",MODE_PRIVATE);

        String prefStudNumber = myprefs.getString("Student Number","");

        if(!prefStudNumber.isEmpty())
        {
            Intent newActivity = new Intent(getApplication(), StudentMain.class);
            startActivity(newActivity);
        }
        else
        {
            studentNumberedt.setText(prefStudNumber);
        }
    }
    //***********************************************************************
    @Override
    protected void onPause()
    {
        //Destroys activity
        super.onPause();
        finish();
    }
    //***********************************************************************
    public void courseClick(View v)
    {
        CourseSingleChoice objCourse = new CourseSingleChoice();
        objCourse.show(getSupportFragmentManager(),"courseDialog");
    }
    //***********************************************************************
    public void btnSaveDetails(View v)
    {
        Student objStudent = new Student();
        objStudent.setStudNumber(studentNumberedt.getText().toString());
        objStudent.setCourseCode(courseItemet.getText().toString());
        objStudent.setStudName(studentNameedt.getText().toString());

        //Validate that all fields are entered in
        if(!objStudent.getStudNumber().isEmpty() && !objStudent.getCourseCode().isEmpty() && !objStudent.getStudName().isEmpty())
        {
            SharedPreferences.Editor editor = myprefs.edit();
            editor.putString("Student Number", objStudent.getStudNumber());
            editor.putString("Course Code", objStudent.getCourseCode());
            editor.putString("Student Name", objStudent.getStudName());
            editor.apply();
            Intent newActivity = new Intent(getApplication(), StudentMain.class);
            startActivity(newActivity);
        }
        else
        {
            Snackbar.make(v,R.string.validation_enter_in_all_fields,Snackbar.LENGTH_SHORT).show();
        }
    }
}
