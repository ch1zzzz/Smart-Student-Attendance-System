package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        else {
            Bundle bundle = getIntent().getExtras();
            int int_month = bundle.getInt("month");
            String monthString = String.valueOf(int_month);

            int int_year = bundle.getInt("year");
            String yearString = String.valueOf(int_year);

            int int_dayOfMonth = bundle.getInt("dayOfMonth");
            String dayOfMonthString = String.valueOf(int_dayOfMonth);

            Calendar calendar = Calendar.getInstance();
            calendar.set(int_year, int_month, int_dayOfMonth);
            date_banner.setText((int_month+1) + "/" + dayOfMonthString + "/" + yearString);
            Date date = calendar.getTime();
            List<CourseOffering> daycourseofferings = new ArrayList<>();
            ArrayList<CourseOffering> dayCO = new ArrayList<>();
            daycourseofferings = myDAO.getCourseOfferingByDay(date);
            for (int i = 0; i < daycourseofferings.size(); i++) {
                dayCO.add(daycourseofferings.get(i));
            }

            StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, dayCO);
            course_offering_date_list.setAdapter(adapter);
            course_offering_date_list.setClickable(true);

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
            course_offering_date_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    List<Date> DateList = myDAO.getTime(dayCO.get(position).getId());
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    for(int i = 1; i < DateList.size();i++) {
                        Calendar calendar_date = Calendar.getInstance();
                        calendar_date.setTime(DateList.get(i));
                        int cal_month = calendar_date.get(Calendar.MONTH);
                        int cal_year = calendar_date.get(Calendar.YEAR);
                        int cal_dayOfMonth = calendar_date.get(Calendar.DAY_OF_MONTH);
                        if((cal_month==int_month)&&(cal_year==int_year)&&(cal_dayOfMonth==int_dayOfMonth)){
                            System.out.println("FOUND CLASS");
                            int cal_hour = calendar_date.get(Calendar.HOUR_OF_DAY);
                            int cal_minute = calendar_date.get(Calendar.MINUTE);
                            Intent intent = new Intent(getApplicationContext(), MarkAbsence.class);
                            intent.putExtra("month",int_month);
                            intent.putExtra("year",int_year);
                            intent.putExtra("dayOfMonth",int_dayOfMonth);
                            intent.putExtra("hourOfDay", cal_hour);
                            intent.putExtra("minute", cal_minute);
                            System.out.println("ALL:" + int_month + "/" + int_dayOfMonth + "/" + int_year + " " + cal_hour + ":" + cal_minute);
                            intent.putExtra("offer_id", String.valueOf(dayCO.get(position).getId()));
                            startActivity(intent);
                        }

                        //String d = dateFormat.format(DateList.get(i)).split("\\s+")[0];
                        //System.out.println(comparison_string);
                        //System.out.println(d);
                        //if(comparison_string.equals(d)){
                        //    String hours_minutes = dateFormat.format(DateList.get(i)).split("\\s+")[1];
                        //    System.out.println(hours_minutes);
                        //    break;
                       // }
                    }
                }
            });
        }
    }
}