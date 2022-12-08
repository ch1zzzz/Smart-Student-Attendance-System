package com.example.ssas_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssas_project.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ClassOfferingViewListAdapter extends ArrayAdapter<Student>{
    private Context mContext;
    private int mResource;

    public ClassOfferingViewListAdapter(Context context, int resource, ArrayList<Student> StudentArrayList){
        super(context, resource, StudentArrayList);
        this.mContext = context;
        this.mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView student_name = convertView.findViewById(R.id.student_name_view1);
        TextView student_id = convertView.findViewById(R.id.student_id_view1);
        TextView student_email = convertView.findViewById(R.id.student_email_view1);

        //Setting the values of List Row
        String str1 = String.format("Student Name: %s", getItem(position).getName());
        student_name.setText(str1);

        String str2 = String.format("Student ID: %s", getItem(position).getId());
        student_id.setText(str2);

        String str3 = String.format("E-mail: %s", getItem(position).getEmail());
        student_email.setText(str3);

        return convertView;
    }
}
