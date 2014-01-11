package com.mirabellehegnet.coursera;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mirabellehegnet.coursera.model.Constants;
import com.mirabellehegnet.coursera.model.Course;
import com.mirabellehegnet.coursera.ui.CourseGridViewAdapter;
import com.mirabellehegnet.coursera.services.XmlCourseReader;

import java.io.InputStream;
import java.util.ArrayList;

public class CoursesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.courses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        GridView gridView;
        ArrayList<Course> courses = new ArrayList<Course>();
        CourseGridViewAdapter courseGridViewAdapter;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
            Intent intent = getActivity().getIntent();
            //getActivity().setTitle(getString(R.string.title_activity_courses));
            getActivity().setTitle(intent.getIntExtra(Constants.Title, R.id.current_courses));
            //getActivity().getSupportActionBar().setTitle(intent.getIntExtra(Constants.Title, 0));
            String file = intent.getStringExtra(Constants.File);
            gridView = (GridView) rootView.findViewById(R.id.coursesGridView);
            try {
                InputStream is = getActivity().getAssets().open(file);
                XmlCourseReader reader = new XmlCourseReader();
                courses = reader.read(is);
                is.close();

                courseGridViewAdapter = new CourseGridViewAdapter(getActivity(), R.layout.list_row, courses);
                gridView.setAdapter(courseGridViewAdapter);
            } catch (Exception e) {
                String msg = e.getMessage();
                e.printStackTrace();

            }
            return rootView;
        }
    }

}
