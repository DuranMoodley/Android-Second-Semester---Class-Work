/*
CourseSingleChoice.java
Displays dialog to user showing a list of courses to select
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */
package attendance.student.prjstudentattendancerecord;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class CourseSingleChoice extends DialogFragment
{
    private final CharSequence [] courseItems = {"DISD3","Bachelor of Education","DISD2"};
    private String studentCourseSelections;
    private EditText courseEdt;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        courseEdt = (EditText) getActivity().findViewById(R.id.edtCourse);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder
                .setTitle("Select Your Course")
                .setSingleChoiceItems(courseItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(which == 0)
                        {
                            studentCourseSelections = courseItems[which].toString();
                        }
                        else if(which == 1)
                        {
                            studentCourseSelections = courseItems[which].toString();
                        }
                        else if(which == 2)
                        {
                            studentCourseSelections = courseItems[which].toString();
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                courseEdt.setText(studentCourseSelections);
            }
        });
        return alertBuilder.create();
    }
    //************************************************************************
}
