package iss.workshop.mymusicservicews;

import java.io.Serializable;

public class Song implements Serializable {

    private final String title;
    private final String desc;
    private final String fname;

    public Song(String title, String desc, String fname) {
        this.title = title;
        this.desc = desc;
        this.fname = fname;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getFname() {
        return fname;
    }
}
