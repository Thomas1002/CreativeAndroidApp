package com.mirabellehegnet.coursera.services;

import android.util.Log;
import android.util.Xml;

import com.mirabellehegnet.coursera.model.Course;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by thomas on 1/3/14.
 */
public class XmlCourseReader {
    public ArrayList<Course> read(InputStream is) throws Exception{
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is, null);
        parser.nextTag();

        ArrayList<Course> courses = new ArrayList<Course>();
        while (parser.next() != XmlPullParser.END_DOCUMENT) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            Log.d("MyTag", name);
            if (parser.getName().equals("course")) {
                courses.add(read(parser));
            }
        }
        return courses;
    }

    public Course read(XmlPullParser parser) throws XmlPullParserException, IOException{
        Course course = new Course();
        //parser.require(XmlPullParser.START_TAG, ns, "entry");
        //int eventType = parser.getEventType();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            //switch (eventType) {
              //  case XmlPullParser.START_TAG:
                    if (name.equals("university")) {
                        course.setUniversity(parser.nextText());
                    } else if (name.equals("title")){
                        course.setTitle(parser.nextText());
                    } else if (name.equals("instructor")){
                        course.setInstructor(parser.nextText());
                    } else if (name.equals("image")) {
                        course.setImage(parser.nextText());
                    } else if (name.equals("duration")) {
                        course.setDuration(parser.nextText());
                    } else if (name.equals("session")) {
                        course.setSession(parser.nextText());
                    } else if (name.equals("about")) {
                        course.setAbout(parser.nextText());
                    } else if (name.equals("description")) {
                        course.setDescription(parser.nextText());
                    }

                //    break;
                //}
           // eventType = parser.next();
            }

        return course;
    }
}
