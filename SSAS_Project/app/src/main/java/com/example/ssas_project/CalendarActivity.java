package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.example.ssas_project.entity.Types;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private DAO myDAO;
    CalendarView simpleCalendarView;
    public void InsertTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 3, 12,0,0);
        Date date = calendar.getTime();
        myDAO.insertTime(1, date);

        calendar.set(2022, 11, 4, 6,0,0);
        date = calendar.getTime();
        myDAO.insertTime(1, date);

        calendar.set(2022, 11, 4, 19,0,0);
        date = calendar.getTime();
        myDAO.insertTime(1, date);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        TextView classesOnXDay = (TextView) findViewById(R.id.calendarTextView);
        myDAO = new MyDAO(this);


        //Create Testing Database for Course Offering
        Course c1 = new Course(1, "CS111 OS", false, 1);
        myDAO.insertCourse(c1);

        CourseOffering c2 = new CourseOffering(1, 1, 40, "JCC101");
        myDAO.insertCourseOffering(c2);

        CourseOffering c3 = new CourseOffering(2, 1, 30, "JCC102");
        myDAO.insertCourseOffering(c3);

        CourseOffering c4 = new CourseOffering(3, 1, 30, "JCC102");
        myDAO.insertCourseOffering(c4);
        InsertTime();
        // get the reference of CalendarView
        //simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the red color for the dates of  focused month
        //simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE); // set the yellow color for the dates of an unfocused month
        //simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED); // red color for the selected week's background
        //simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN); // green color for the week separator line
        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                classesOnXDay.setText("Classes on " + (month+1) + " " + dayOfMonth + " " + year);
            }
        });

    }


}