/*
ViewPageAdapter.java
Controls sliding tab layout selection class
Student: Duran Moodley  Student Number: 13016335
Lecturer : Rajesh Chanderman
Assignment : 1
Date Updated : 9/4/16
 */
package attendance.student.prjstudentattendancerecord;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPageAdapter extends FragmentStatePagerAdapter{

    private final CharSequence[] pageTitles ;
    private final int numberOfPages;
    //***************************************************************************************
    public ViewPageAdapter(FragmentManager fm, CharSequence titles[], int pages) {
        super(fm);
        pageTitles = titles;
        numberOfPages = pages;
    }
    //***************************************************************************************
    @Override
    public Fragment getItem(int position)
    {
        //Instantiate object when user slides on the slider
        FragStudentCheckIn checkIn = new FragStudentCheckIn();
        if(position == 0)
        {
            return checkIn;
        }
        else if(position == 1)
        {
            return new FragStudentAtttendanceReport();
        }

        return checkIn;
    }
    //***************************************************************************************
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
    //***************************************************************************************
    @Override
    public int getCount() {
        return numberOfPages;
    }
}
