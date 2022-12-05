package com.example.ssas_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssas_project.entity.CourseOffering;

import java.util.ArrayList;
import java.util.List;

public class StudentViewListAdapter extends ArrayAdapter<CourseOffering> {
    private Context mContext;
    private int mResource;

    public StudentViewListAdapter(Context context, int resource, ArrayList<CourseOffering> CourseOfferingArrayList){
        super(context, resource, CourseOfferingArrayList);
        this.mContext = context;
        this.mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView course_id = convertView.findViewById(R.id.student_class_id);
        TextView courseoffering_id = convertView.findViewById(R.id.student_class_offeringid);
        TextView courseoffering_students = convertView.findViewById(R.id.student_classoffering_studentnumber);
        TextView courseoffering_room = convertView.findViewById(R.id.student_classoffering_room);

        //Setting the values of List Row
        String str1 = String.format("Course Offering ID: %s", getItem(position).getId());
        course_id.setText(str1);

        String str2 = String.format("Course ID: %s", getItem(position).getCourse_id());
        courseoffering_id.setText(str2);

        String str3 = String.format("Number of students: %s", getItem(position).getStudentsNum());
        courseoffering_students.setText(str3);

        String str4 = String.format("Classroom: %s", getItem(position).getClassroom());
        courseoffering_room.setText(str4);

        return convertView;
    }
}
