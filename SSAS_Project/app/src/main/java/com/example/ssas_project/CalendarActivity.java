package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private DAO myDAO;
    CalendarView simpleCalendarView;
    private Button back_to_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        TextView classesOnXDay = (TextView) findViewById(R.id.calendarTextView);
        back_to_class = findViewById(R.id.back_to_class);
        myDAO = new MyDAO(this);
        List<CourseOffering> courseOffering_DayList = new ArrayList<>();
        ArrayList<CourseOffering> array_courses = new ArrayList<>();
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                Date date = calendar.getTime();
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                classesOnXDay.setText("Classes on " + (month+1) + " " + dayOfMonth + " " + year);
                /*
                courseOffering_DayList = myDAO.getCourseOfferingByDay(date);
                for (int i = 0; i < courseOffering_DayList.size(); i++) {
                    CourseOffering temp = courseOffering_DayList.get(i);
                    array_courses.add(temp);
                }
                 */
            }
        });
        back_to_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, ClassActivity.class);
                startActivity(intent);
            }
        });


    }


}