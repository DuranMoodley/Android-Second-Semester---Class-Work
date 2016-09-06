/*
FragStudentAttendanceReport.java
Displays user attendance report, communicates with online database and displays data
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */
package attendance.student.prjstudentattendancerecord;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragStudentAtttendanceReport extends Fragment
{
    private View view;
    private ListView studentReportlst;
    private SharedPreferences myprefs;
    //******************************************************************************
    public FragStudentAtttendanceReport()
    {
        // Required empty public constructor
    }
    //******************************************************************************
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_attendance_report, container, false);
        //reportTv = (TextView) view.findViewById(R.id.tvReport);
        getActivity();
        myprefs = getActivity().getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        studentReportlst = (ListView) view.findViewById(R.id.lstStudReport);
        new RetrieveReportData().execute(myprefs.getString("Student Number",""));
        return view;
    }
    //******************************************************************************
    private class RetrieveReportData extends AsyncTask<String,Void,ArrayList>
    {
        @Override
        protected void onPreExecute() {

        }
        //*****************************************************************************
        @Override
        protected ArrayList doInBackground(String... params)
        {
            String line;
            ArrayList objReport = new ArrayList();
            String entireLine = "";
            JSONObject jsonObject;
            JSONArray jsonArray;
            String studentReport;
            String app_data;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://www.duran.dx.am/jsonstudents.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                //Write the data/post which is the student number to the url
                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter objWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                app_data = URLEncoder.encode("Stud_Number","UTF-8")+"="+URLEncoder.encode(params[0],"UTF-8");
                objWriter.write(app_data);
                objWriter.flush();
                objWriter.close();
                outputStream.close();

                BufferedReader objread = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((line = objread.readLine()) != null) {
                    entireLine += line;
                }

                //Get json elements
                jsonObject = new JSONObject(entireLine);
                jsonArray = jsonObject.optJSONArray("students");

                for(int count = 0 ; count < jsonArray.length();count++)
                {
                    JSONObject jsonStudentData = jsonArray.getJSONObject(count);
                    studentReport = jsonStudentData.optString("Course_Code")
                            + "\n" + jsonStudentData.optString("Module_Code")
                            + "\n" + jsonStudentData.optString("CheckIn_Time")
                            + "\n" + jsonStudentData.optString("CheckOut_Time")
                            + "\n" + jsonStudentData.getString("Date");
                    objReport.add(studentReport);
                }
                objread.close();
                return objReport;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                assert urlConnection != null;
                urlConnection.disconnect();
            }

            return objReport;
        }
        //**************************************************************************************
        @Override
        protected void onPostExecute(ArrayList arrayList)
        {
            ArrayAdapter<String> arrAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            studentReportlst.setAdapter(arrAdapter);
        }
    }
}
