package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;

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

public class StudentData extends AppCompatActivity {

    private DAO myDAO;
    private TextView edit_offer_id, edit_viewdata;
    private Button edit_backbutton;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);

        myDAO = new MyDAO(this);

        float attend_count = 0;
        float miss_count = 0;

        //Connect the variables to their ID
        edit_backbutton = findViewById(R.id.student_data_backbutton);
        edit_viewdata = findViewById(R.id.student_data_table);
        edit_offer_id = findViewById(R.id.student_data_offerid);
        pieChart = findViewById(R.id.student_data_chart);

        //Retrieve ID from the last activity
        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else{
            //Retrieve value from last activity
            Bundle bundle = getIntent().getExtras();
            String offer_id = bundle.getString("offer_id");
            String student_id = bundle.getString("student_id");

            String str1 = String.format("Course Offering ID: %s", offer_id);
            edit_offer_id.setText(str1);

            //Get the Date of the course offering
            Map<Date, Boolean> offering_map = myDAO.getAttendance(Integer.parseInt(student_id), Integer.parseInt(offer_id));
            ArrayList<PieEntry> attend_data = new ArrayList<>();

            if(!offering_map.isEmpty()){
                for(Map.Entry<Date, Boolean> entry : offering_map.entrySet()){
                    if(entry.getValue() == true){
                        attend_count += 1;
                    }
                    else{
                        miss_count += 1;
                    }
                }
                float attend_percent = (attend_count/(attend_count + miss_count)) * 100;
                float miss_percent = (miss_count/(attend_count + miss_count)) * 100;
                attend_data.add(new PieEntry(attend_percent, "Attend"));
                attend_data.add(new PieEntry(miss_percent, "Miss"));

                PieDataSet pieDataSet = new PieDataSet(attend_data, "Attendance Status");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextColor(Color.BLACK);
                pieDataSet.setValueTextSize(16f);

                PieData pieData = new PieData(pieDataSet);
                pieChart.setData(pieData);
                pieChart.getDescription().setEnabled(false);
                pieChart.setCenterText("Attendance Percentage");
                pieChart.animate();
            }

            //Set on click listener for Back Button
            //Click listener to the back button
            edit_backbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentData.this, StudentView.class);
                    intent.putExtra("student_id", student_id);
                    intent.putExtra("offer_id", offer_id);
                    startActivity(intent);
                }
            });

            //Set on click listener for View Data
            edit_viewdata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentData.this, StudentDataTable.class);
                    intent.putExtra("student_id", student_id);
                    intent.putExtra("offer_id", offer_id);
                    startActivity(intent);
                }
            });

        }
    }
}