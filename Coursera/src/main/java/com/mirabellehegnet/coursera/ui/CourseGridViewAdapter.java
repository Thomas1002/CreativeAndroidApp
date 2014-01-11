package com.mirabellehegnet.coursera.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mirabellehegnet.coursera.R;
import com.mirabellehegnet.coursera.model.Course;

import java.util.ArrayList;

/**
 * Created by thomas on 1/2/14.
 */
public class CourseGridViewAdapter extends ArrayAdapter<Course>  {
    Context context;
    int layoutResourceId;
    ArrayList<Course> courses = new ArrayList<Course>();

    public CourseGridViewAdapter(Context context, int layoutResourceId, ArrayList<Course> courses) {
        super(context, layoutResourceId, courses);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.courses = courses;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new RecordHolder();
            holder.university = (TextView) row.findViewById(R.id.university);
            holder.description = (TextView) row.findViewById(R.id.description);
            holder.instructor = (TextView) row.findViewById(R.id.instructor);
            holder.duration = (TextView) row.findViewById(R.id.durationTextView);
            holder.session = (TextView) row.findViewById(R.id.session);
	   /*holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
	   holder.imageItem = (ImageView) row.findViewById(R.id.item_image);*/
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        Course course = courses.get(position);
        holder.university.setText(course.getUniversity());
        holder.description.setText(course.getTitle());
        holder.instructor.setText("with " + course.getInstructor());


        //holder.image.setImageBitmap(course.getImage());
        return row;

    }

    static class RecordHolder {
        TextView university;
        ImageView image;
        TextView description;
        TextView instructor;
        TextView duration;
        TextView session;
    }

}
