package com.mirabellehegnet.coursera;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mirabellehegnet.coursera.model.Constants;
import com.mirabellehegnet.coursera.model.Course;
import com.mirabellehegnet.coursera.ui.CourseGridViewAdapter;
import com.mirabellehegnet.coursera.services.XmlCourseReader;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = createIntent(id);
        if (intent != null) {
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Intent createIntent(int id)
    {
        String file;
        int title = 0;
        switch (id) {
            case R.id.current_courses:
                file = "current.xml";
                title = R.string.current_courses_title;
                break;
            case R.id.past_courses:
                file = "past.xml";
                title = R.string.past_courses_title;
                break;
            case R.id.upcoming:
                file = "upcoming.xml";
                title = R.string.upcoming_courses_title;
                break;
            default:
                return null;
        }
        Intent intent = new Intent(this, CoursesActivity.class);
        intent.putExtra(Constants.Title, title);
        intent.putExtra(Constants.File, file);
        return intent;
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
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            gridView = (GridView) rootView.findViewById(R.id.gridView1);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.d("MyLog", "Position " + i);
                    Intent intent = new Intent(getActivity(), CurrentActivity.class);
                    intent.putExtra(Constants.Course, courses.get(i));
                    startActivity(intent);
                }
            });


            //gridView = (GridView) findViewById(R.id.gridView1);

            /*
            try {
                InputStream is = getActivity().getAssets().open("courses.json");

                JsonCourseReader reader = new JsonCourseReader();

                courses = reader.readJsonStream(is);
                courses.add(new Course("University of Illinois at Urbana-Champaign", "Creative, Serious and Playful Science of Android Apps", "Lawrence Angrave"));

                is.close();
                courseGridViewAdapter = new CourseGridViewAdapter(getActivity(), R.layout.list_row, courses);
                gridView.setAdapter(courseGridViewAdapter);
            } catch (IOException e) {
                String msg = e.getMessage();
                e.printStackTrace();

            }*/

            try {
                InputStream is = getActivity().getAssets().open("courses.xml");
                XmlCourseReader reader = new XmlCourseReader();
                courses = reader.read(is);
                is.close();

                //courses.add(new Course("University of Illinois at Urbana-Champaign", "Creative, Serious and Playful Science of Android Apps", "Lawrence Angrave"));
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
