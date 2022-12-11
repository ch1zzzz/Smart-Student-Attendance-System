package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;
import com.example.ssas_project.entity.Student;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.*;


public class MarkAbsence extends AppCompatActivity {

    private TextView offerid, date;
    private Button back_button, submit_button, update_button;
    private TableLayout mark_table;
    private DAO myDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_absence);

        myDAO = new MyDAO(this);
        //Initialize variables with the ID layout
        //Text
        offerid = findViewById(R.id.markabsence_offerid);
        date = findViewById(R.id.markabsence_date);
        //Button
        back_button = findViewById(R.id.markabsence_back);
        submit_button = findViewById(R.id.markabsence_submit);
        update_button = findViewById(R.id.markabsence_update);
        //Table Layout
        mark_table = findViewById(R.id.markabsence_table);

        //Retrieve ID from the last activity
        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else{
            //Retrieve value from last activity
            Bundle bundle = getIntent().getExtras();
            String offer_id = bundle.getString("offer_id");

            //Get the Date key
            int month = bundle.getInt("month");
            String monthString = String.valueOf(month);

            int year = bundle.getInt("year");
            String yearString = String.valueOf(year);

            int dayOfMonth = bundle.getInt("dayOfMonth");
            String dayOfMonthString = String.valueOf(dayOfMonth);

            int hourOfDay = bundle.getInt("hourOfDay");
            int minute    = bundle.getInt("minute");


            //Set Course ID Text
            String str1 = String.format("Course Offering ID: %s", offer_id);
            offerid.setText(str1);

            String str2 = "Date: "+ month + "/" + dayOfMonth + "/" + year;
            date.setText(str2);

            //Update Table
            ArrayList<Student> array_students = new ArrayList<>();
            List<List<Integer>> student_list = new ArrayList<>();
            Map<Integer, Boolean> student_check = new HashMap<Integer, Boolean>();

            //Add all the students into the list
            student_list.add(myDAO.getEnrollStudents(Integer.parseInt(offer_id)));

            //Iterate through the list and add to Arraylist and the students info
            for(int i = 0; i < student_list.size(); i ++){
                List<Integer> list = student_list.get(i);
                for(int j = 0; j < list.size(); j++){
                    array_students.add(myDAO.getStudent(list.get(j)));
                }
            }

            //Add Header to the Table
            TextView student_header = new TextView(getApplicationContext());
            student_header.setText("Student Name");
            student_header.setTextColor(Color.BLACK);
            student_header.setGravity(Gravity.CENTER_HORIZONTAL);
            student_header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));

            //Create Row and Add Header to Row
            TableRow headerRow = new TableRow(getApplicationContext());
            headerRow.setBackgroundColor(Color.GRAY);
            headerRow.addView(student_header);

            mark_table.addView(headerRow);

            //TextView[] CheckBoxArray    = new TextView[array_students.size()];
            CheckBox[] CheckBoxArray = new CheckBox[array_students.size()];
            TableRow[] tr_head      = new TableRow[array_students.size()];

            //Set Default Mapping to false
            if(!array_students.isEmpty()){
                for(int i = 0; i < array_students.size(); i++){
                    student_check.put(array_students.get(i).getId(), false);
                }
            }

            //Putting Data into the Table
            if(!array_students.isEmpty()){
                for(int i = 0; i < array_students.size(); i ++){
                    //Create Table Row
                    tr_head[i] = new TableRow(this);
                    //Create TextView Row
                    //Date Row and insert Student names to the table
                    CheckBoxArray[i] = new CheckBox(this);
                    CheckBoxArray[i].setTextColor(Color.BLACK);
                    CheckBoxArray[i].setGravity(Gravity.CENTER_HORIZONTAL);
                    CheckBoxArray[i].setPadding(20,20,20,20);
                    CheckBoxArray[i].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
                    CheckBoxArray[i].setText(array_students.get(i).getName());
                    int id = array_students.get(i).getId();

                    CheckBoxArray[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(buttonView.isChecked()){
                                student_check.put(id, true);
                                student_header.setText(String.valueOf(id));
                            }
                            else{
                                student_check.put(id, false);
                            }
                        }
                    });

                    //Add view to Table Row and Layout
                    tr_head[i].addView(CheckBoxArray[i]);
                    mark_table.addView(tr_head[i]);
                }
            }

            //Set on click listener for Back Button
            //Click listener to the back button
            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, 0, dayOfMonth);


                    Intent intent = new Intent(MarkAbsence.this, StudentIDLookup.class);
                    //Send Data to next activity
                    intent.putExtra("offer_id", offer_id);
                    intent.putExtra("month", calendar.get(Calendar.MONTH));
                    intent.putExtra("year", calendar.get(Calendar.YEAR));
                    intent.putExtra("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH));
                    //Start the next activity
                    startActivity(intent);
                }
            });
            //Submit the data button
            submit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month - 1, dayOfMonth, hourOfDay, minute, 0);
                    Date date = calendar.getTime();

                    //Mark Attendance to the Database
                    for(Map.Entry<Integer, Boolean> entry : student_check.entrySet()){
                        myDAO.insertAttendance(entry.getKey(), Integer.parseInt(offer_id), date, entry.getValue());
                    }

                    Intent intent = new Intent(MarkAbsence.this, StudentIDLookup.class);
                    //Send Data to next activity
                    intent.putExtra("offer_id", offer_id);
                    intent.putExtra("month", calendar.get(Calendar.MONTH));
                    intent.putExtra("year", calendar.get(Calendar.YEAR));
                    intent.putExtra("dayOfMonth", calendar.get(Calendar.DAY_OF_MONTH));
                    intent.putExtra("hourOfDay", hourOfDay);
                    intent.putExtra("minute", minute);
                    //Start the next activity
                    startActivity(intent);

                }
            });

            //Update the data button
            update_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month - 1, dayOfMonth, hourOfDay, minute, 0);
                    Date date = calendar.getTime();

                    //Mark Attendance to the Database
                    for(Map.Entry<Integer, Boolean> entry : student_check.entrySet()){
                        myDAO.updateAttendance(entry.getKey(), Integer.parseInt(offer_id), date, entry.getValue());
                    }

                    Intent intent = new Intent(MarkAbsence.this, StudentIDLookup.class);
                    //Send Data to next activity
                    intent.putExtra("offer_id", offer_id);
                    intent.putExtra("month", month);
                    intent.putExtra("year", year);
                    intent.putExtra("dayOfMonth", dayOfMonth);
                    intent.putExtra("hourOfDay", hourOfDay);
                    intent.putExtra("minute", minute);

                    //Start the next activity
                    startActivity(intent);
                }
            });

        }
    }
}