package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Tutorials {
    private int id;
    private String title;
    private String videourl;
    private String desc;
    private String source;
    private String formattedCreatedAt;
    private long createdat;

    public Tutorials(String title, String videourl, String desc, String source) {
        this.title = title;
        this.videourl = videourl;
        this.desc = desc;
        this.source = source;
        this.createdat = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    // Create comparison

//    @Override
//    public int compareTo(Tutorials tutorialsObj) {
//        if (this.createdat < tutorialsObj.createdat)
//        {
//            return -1; //this object was made earlier than the second object.
//        }
//        else if (this.createdat > tutorialsObj.createdat){ //this object was made later than the second object
//            return 1;
//        }
//        else {
//            return 0; //they were made at the same time, which is very unlikely, but mathematically not impossible.
//        }
//    }

    // Hashcode n equals Override

    @Override
    public boolean equals (Object object) {
        if (this == object) return true;
        if (!(object instanceof Tutorials)) return false;
        Tutorials tutorials = (Tutorials) object;
        return id == tutorials.id &&
                Objects.equals(title, tutorials.title) &&
                Objects.equals(videourl, tutorials.videourl) &&
                Objects.equals(desc, tutorials.desc) &&
                Objects.equals(source, tutorials.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, videourl, desc, source);
    }

    //Getters


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVideourl() {
        return videourl;
    }

    public String getDesc() {
        return desc;
    }

    public String getSource() {
        return source;
    }

    public long getCreatedat() {
        return createdat;
    }

    public String getFormattedCreatedAt(){
        Date date = new Date(createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a"; //see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCreatedat() {
        this.createdat = System.currentTimeMillis(); // It'll become clear soon why we need this explicit setter
    }

    public void setFormattedCreatedAt(){
        Date date = new Date(this.createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }
}

//
