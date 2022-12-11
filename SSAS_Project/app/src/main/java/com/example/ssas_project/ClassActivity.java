package com.example.ssas_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.Course;
import com.example.ssas_project.entity.CourseOffering;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClassActivity extends AppCompatActivity {
    private DAO myDAO;
    private ListView edit_listview;
    private Button sign_out_button, add_class_button, calendar_button, extract_data_button ;
    private TextView banner;
    List list = new ArrayList();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        myDAO = new MyDAO(this);

        sign_out_button = findViewById(R.id.sign_out_button);
        edit_listview = findViewById(R.id.teacher_course_list_view);
        add_class_button = findViewById(R.id.newclass_button);
        calendar_button = findViewById(R.id.calendar_button);
        extract_data_button = findViewById(R.id.Extract_Database);
        banner = findViewById(R.id.class_banner);
        //banner.setText("Welcome " + myDAO)
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            Bundle extras = intent.getExtras();
            int class_id = Integer.parseInt(extras.getString("id"));
            String class_name = extras.getString("name");
            int class_num_offerings = Integer.parseInt(extras.getString("offerings"));
            Course temp = new Course(class_id, class_name, true,class_num_offerings );
            myDAO.insertCourse(temp);
        }
        //Update List View
        ArrayList<Course> array_courses = new ArrayList<>();

        List<Course> courses_list = myDAO.getCourse();
        for (int i = 0; i < courses_list.size(); i++) {
            Course temp = courses_list.get(i);
            array_courses.add(temp);
        }
        TeacherViewListAdapter adapter = new TeacherViewListAdapter(this, R.layout.teacher_class_item, array_courses);
        if(array_courses.size()>=1) {
            edit_listview.setAdapter(adapter);
            edit_listview.setClickable(true);
        }
        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        add_class_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, Class_Parameters_Activity.class);
                startActivity(intent);
            }
        });
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
        extract_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.setType("message/rfc822");

                String filename = Constants.DATABASE_NAME;
                String pathname = getApplicationContext().getApplicationInfo().dataDir + "/databases";
                String fileLocation = pathname + "/" + filename;
                File data = new File(fileLocation);
                Uri uri = FileProvider.getUriForFile(getApplicationContext(), "com.example.ssas_project.provider", data);
                i.putExtra(Intent.EXTRA_SUBJECT, "SSAS DATA");
                i.putExtra(Intent.EXTRA_TEXT   , "The attachment contains the database file for App SSAS.");
                i.putExtra(Intent.EXTRA_STREAM, uri);
                try {
                    startActivity(Intent.createChooser(i, "Downloading All Data And Send Mail"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ClassActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}