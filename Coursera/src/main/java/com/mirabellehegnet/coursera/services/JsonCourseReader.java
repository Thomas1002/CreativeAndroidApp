package com.mirabellehegnet.coursera.services;

import android.util.JsonReader;
import android.util.Log;

import com.mirabellehegnet.coursera.model.Course;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by thomas on 1/2/14.
 */
public class JsonCourseReader {

    public ArrayList<Course> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        reader.setLenient(true);
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                courses.add(readMessage(reader));
            }
            reader.endArray();
        } catch(Exception ex) {
            Log.e("MyTag", ex.getMessage());
            ex.printStackTrace();
        } finally {
            reader.close();
        }
        return courses;
    }

    public Course readMessage(JsonReader reader) throws IOException {
        long id = -1;
        String title = null;
        String instructor = null;
        String university = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                id = reader.nextLong();
            } else if (name.equals("title")) {
                title = reader.nextString();
            } else if (name.equals("user")) {
                instructor = reader.nextString();//readUser(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Course(university, title, instructor);

    }

    public String readUser(JsonReader reader) throws IOException {
        String username = null;
        int followersCount = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                return reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return "";
    }
}
