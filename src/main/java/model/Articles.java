package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Articles {
    private int id;
    private String headline;
    private String documentation;
    private String imgurl;
    private String author;
    private long createdat;
    private String formattedCreatedAt;

    public Articles(String headline, String documentation, String imgurl, String author) {
        this.headline = headline;
        this.documentation = documentation;
        this.imgurl = imgurl;
        this.author = author;
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
        if (!(object instanceof Articles)) return false;
        Articles articles = (Articles) object;
        return id == articles.id &&
                Objects.equals(headline, articles.headline) &&
                Objects.equals(imgurl, articles.imgurl) &&
                Objects.equals(documentation, articles.documentation) &&
                Objects.equals(author, articles.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, headline, documentation, imgurl, author);
    }

    //Getters


    public int getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getAuthor() {
        return author;
    }

    public String getimgurl() {
        return imgurl;
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

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

//    public void setimgurl(String imgurl) {
//        this.imgurl = imgurl;
//    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setAuthor(String author) {
        this.author = author;
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
