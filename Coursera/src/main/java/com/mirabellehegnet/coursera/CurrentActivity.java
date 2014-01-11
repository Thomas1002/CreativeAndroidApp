package com.mirabellehegnet.coursera;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mirabellehegnet.coursera.model.Constants;
import com.mirabellehegnet.coursera.model.Course;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CurrentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.current, menu);
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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_current, container, false);
            Course course = (Course) getActivity().getIntent().getSerializableExtra(Constants.Course);

            TextView universityTextView = (TextView) rootView.findViewById(R.id.university);
            TextView titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
            TextView durationTextView = (TextView) rootView.findViewById(R.id.durationTextView);
            TextView sessionTextView = (TextView) rootView.findViewById(R.id.sessionTextView);
            TextView aboutTextView = (TextView) rootView.findViewById(R.id.aboutTextView);
            TextView descriptionTextView = (TextView) rootView.findViewById(R.id.descriptionTextView);
            TextView instructorTextView = (TextView) rootView.findViewById(R.id.instructorTextView);
            universityTextView.setText(course.getUniversity());
            titleTextView.setText(course.getTitle());
            descriptionTextView.setText(course.getDescription());
            sessionTextView.setText(course.getSession());
            instructorTextView.setText(course.getInstructor());

            DateFormat df = DateFormat.getDateInstance();
            String session = df.format(course.getDate());
            String duration = String.format("%s (%s weeks long)",session, course.getDuration());
            durationTextView.setText(duration);
            aboutTextView.setText(course.getAbout());

            return rootView;
        }
    }

}
