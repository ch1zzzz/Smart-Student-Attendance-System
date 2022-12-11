package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateActivity extends AppCompatActivity {
    private DAO myDAO;
    private TextView date_banner;
    private Button back_to_classes, back_to_calendar;
    private ListView course_offering_date_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        myDAO = new MyDAO(this);
        date_banner = findViewById(R.id.date_view_banner);
        course_offering_date_list = findViewById(R.id.courseclasslist);
        back_to_calendar= findViewById(R.id.back_to_calendar);
        back_to_classes = findViewById(R.id.back_to_classes);
        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else {
            Bundle bundle = getIntent().getExtras();
            int month = bundle.getInt("month");
            String monthString = String.valueOf(month);

            int year = bundle.getInt("year");
            String yearString = String.valueOf(year);

            int dayOfMonth = bundle.getInt("dayOfMonth");
            String dayOfMonthString = String.valueOf(dayOfMonth);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            date_banner.setText(month + "/" + dayOfMonth + "/" + year);
            Date date = calendar.getTime();
            List<CourseOffering> daycourseofferings = new ArrayList<>();
            ArrayList<CourseOffering> dayCO = new ArrayList<>();
            daycourseofferings = myDAO.getCourseOfferingByDay(date);
            for(int i = 0; i < daycourseofferings.size(); i ++) {
                dayCO.add(daycourseofferings.get(i));
            }
            StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, dayCO);
            course_offering_date_list.setAdapter(adapter);
            course_offering_date_list.setClickable(true);
        }
        back_to_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        back_to_classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DateActivity.this, ClassActivity.class);
                startActivity(intent);
            }
        });
    }
}