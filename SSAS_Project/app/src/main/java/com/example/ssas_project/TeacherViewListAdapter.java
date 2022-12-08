package com.example.ssas_project;

import static java.lang.String.valueOf;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ssas_project.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class TeacherViewListAdapter extends ArrayAdapter<Course> {
    private Context mContext;
    private int mResource;

    public TeacherViewListAdapter(Context context, int resource, ArrayList<Course> CourseArrayList){
        super(context, resource, CourseArrayList);
        this.mContext = context;
        this.mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView course_id_bold = convertView.findViewById(R.id.teacher_class_id);
        TextView course_name_bold = convertView.findViewById(R.id.teacher_class_name_bold);
        TextView course_number_offerings_bold = convertView.findViewById(R.id.teacher_class_offering_bold);
        TextView course_id = convertView.findViewById(R.id.teacher_classid);
        TextView course_name = convertView.findViewById(R.id.teacher_class_name);
        TextView course_number_offerings = convertView.findViewById(R.id.teacher_class_offering_id);

        //Setting the values of List Row
        course_id_bold.setText("Class ID: ");
        course_name_bold.setText("Class Name: ");
        course_number_offerings_bold.setText("# Offerings: ");


        String str1 = valueOf(getItem(position).getId());
        course_id.setText(str1);

        String str2 = valueOf(getItem(position).getName());
        course_name.setText(str2);

        String str3 = valueOf(getItem(position).getNumberOfferings());
        course_number_offerings.setText(str3);


        return convertView;
    }
}
