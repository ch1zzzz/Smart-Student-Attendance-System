package com.example.ssas_project;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssas_project.database.DAO;
import com.example.ssas_project.database.MyDAO;
import com.example.ssas_project.entity.CourseOffering;

import java.util.ArrayList;
import java.util.List;

public class DeleteOfferingActivity extends AppCompatActivity{
    private EditText course_off_id1;
    private ListView offering_list;
    private Button del_button, back_button2;
    private DAO myDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.del_offering_page);

        course_off_id1 = findViewById(R.id.del_offering_id);
        offering_list = findViewById(R.id.offering_view_list);
        del_button = findViewById(R.id.delete_offering_button);
        back_button2 = findViewById(R.id.back_button124);

        myDAO = new MyDAO(this);

        Intent pre_intent = getIntent();
        if(pre_intent.getExtras() == null){
        }
        else {

            Bundle bundle = getIntent().getExtras();
            String search_class_offering = bundle.getString("course_id");

            ArrayList<CourseOffering> array_courseoffering = new ArrayList<>();
            List<List<Integer>> courseoffering_list = new ArrayList<>();

            courseoffering_list.add((myDAO.getCourseofferingByCourseID(Integer.parseInt(search_class_offering))));

            for (int i = 0; i < courseoffering_list.size(); i++) {
                List<Integer> list = courseoffering_list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    array_courseoffering.add(myDAO.getCourseOffering(list.get(j)));
                }
            }

            StudentViewListAdapter adapter = new StudentViewListAdapter(this, R.layout.student_class_item, array_courseoffering);
            offering_list.setAdapter(adapter);
            offering_list.setClickable(true);

            back_button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DeleteOfferingActivity.this, CoursePageActivity.class);
                    intent.putExtra("course_id", search_class_offering);
                    startActivity(intent);
                }
            });

            del_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String del_offer = course_off_id1.getText().toString();
                    if(del_offer.isEmpty()){
                        Toast.makeText(DeleteOfferingActivity.this, "Please enter the required field!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        CourseOffering id_verify = myDAO.getCourseOffering(Integer.parseInt(del_offer));
                        if(id_verify != null) {
                            myDAO.deleteCourseOffering(Integer.parseInt(del_offer));
                            Toast.makeText(DeleteOfferingActivity.this, "Course Offering "+del_offer+" was deleted!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DeleteOfferingActivity.this, CoursePageActivity.class);
                            intent.putExtra("course_id", search_class_offering);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(DeleteOfferingActivity.this, "Class offering does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
