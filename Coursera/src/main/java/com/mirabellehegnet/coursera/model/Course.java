package com.mirabellehegnet.coursera.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Created by thomas on 1/2/14.
 */
public class Course implements Serializable {
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    private String title;
    private String instructor;
    private String university;
    private String image;
    private String duration;
    private String session;

    private String description;
    private String about;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDuration() {
        return duration;
    }

    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(session);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Course(String university, String title, String instructor) {

        this.university = university;
        this.title = title;
        this.instructor = instructor;
    }

    public Course() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
