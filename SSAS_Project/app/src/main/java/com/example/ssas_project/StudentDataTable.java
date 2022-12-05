package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow.LayoutParams;
import android.widget.TableRow;

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
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Set;
import android.os.Bundle;

public class StudentDataTable extends AppCompatActivity {

    private DAO myDAO;
    private TextView edit_offer_id;
    private Button edit_back;
    private TableLayout edit_tablelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_table);

        myDAO = new MyDAO(this);

        int count = 0;

        //Connect the variables to their ID
        edit_back = findViewById(R.id.attend_table_back);
        edit_offer_id = findViewById(R.id.student_table_offerid);
        edit_tablelayout = findViewById(R.id.attend_table);

        //Retrieve ID from the last activity
        Intent pre_intent = getIntent();

        if(pre_intent.getExtras() == null){
        }
        else{
            //Retrieve value from last activity
            Bundle bundle = getIntent().getExtras();
            String offer_id = bundle.getString("offer_id");
            String student_id = bundle.getString("student_id");

            //Set Course ID Text
            String str1 = String.format("Course Offering ID: %s", offer_id);
            edit_offer_id.setText(str1);

            //Get the Date of the course offering
            Map<Date, Boolean> offering_map = myDAO.getAttendance(Integer.parseInt(student_id), Integer.parseInt(offer_id));

            //Add Header to the Table
            TextView date_header = new TextView(getApplicationContext());
            date_header.setText("Date");
            date_header.setTextColor(Color.BLACK);
            date_header.setGravity(Gravity.CENTER_HORIZONTAL);
            date_header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));

            TextView attend_header = new TextView(getApplicationContext());
            attend_header.setText("Attendance Status");
            attend_header.setTextColor(Color.BLACK);
            attend_header.setGravity(Gravity.CENTER_HORIZONTAL);
            attend_header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));

            TableRow headerRow = new TableRow(getApplicationContext());
            headerRow.setBackgroundColor(Color.GRAY);
            headerRow.addView(date_header);
            headerRow.addView(attend_header);

            edit_tablelayout.addView(headerRow);

            TextView[] DateArray    = new TextView[offering_map.size()];
            TextView[] AttendArray  = new TextView[offering_map.size()];
            TableRow[] tr_head      = new TableRow[offering_map.size()];

            //Implementing Data into the Table
            if(!offering_map.isEmpty()){
                for(Map.Entry<Date, Boolean> entry : offering_map.entrySet()){
                    String status;

                    //Boolean to String
                    if(entry.getValue() == true){
                        status = "Attend";
                    }
                    else{
                        status = "Miss";
                    }

                    //Create Table Row
                    tr_head[count] = new TableRow(this);

                    //Create TextView Row
                    //Date Row
                    DateArray[count] = new TextView(this);
                    DateArray[count].setTextColor(Color.BLACK);
                    DateArray[count].setGravity(Gravity.CENTER_HORIZONTAL);
                    DateArray[count].setPadding(20,20,20,20);
                    DateArray[count].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
                    DateArray[count].setText(entry.getKey().toString());

                    //Attend Row
                    AttendArray[count] = new TextView(this);
                    AttendArray[count].setTextColor(Color.BLACK);
                    AttendArray[count].setGravity(Gravity.CENTER_HORIZONTAL);
                    AttendArray[count].setPadding(20,20,20,20);
                    AttendArray[count].setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
                    AttendArray[count].setText(status);

                    //Add view to Table Row and Layout
                    tr_head[count].addView(DateArray[count]);
                    tr_head[count].addView(AttendArray[count]);

                    edit_tablelayout.addView(tr_head[count]);

                    count +=1;
                }
            }

            //Set on click listener for Back Button
            //Click listener to the back button
            edit_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentDataTable.this, StudentData.class);
                    intent.putExtra("student_id", student_id);
                    intent.putExtra("offer_id", offer_id);
                    startActivity(intent);
                }
            });

        }
    }
}