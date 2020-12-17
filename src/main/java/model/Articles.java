package model;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.SimpleFormatter;

public class Articles {
    private String headline;
    private String documentation;
    private Blob imageurl;
    private String author;
    private int id;
    private long createdat;
    private String formattedCreatedAt;

    public  Articles(String headline, String documentation, Blob imageurl, String author) {
        this.headline = headline;
        this.documentation = documentation;
        this.imageurl= imageurl;
        this.author = author;
        this.createdat = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    //Create comparison

//    @Override
//    public int compareTo(Articles articlesObject) {
//        if (this.createdat < articlesObject.createdat)
//        {
//            return -1; //this object was made earlier than the second object.
//        }
//        else if (this.createdat > articlesObject.createdat){ //this object was made later than the second object
//            return 1;
//        }
//        else {
//            return 0; //they were made at the same time, which is very unlikely, but mathematically not impossible.
//        }
//    }

    //Hashcode n equals override

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Articles)) return false;
        Articles articles = (Articles) object;
        return id == articles.id &&
                Objects.equals(headline, articles.headline) &&
                Objects.equals(documentation, articles.documentation) &&
                Objects.equals(imageurl, articles.imageurl) &&
                Objects.equals(author, articles.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline, documentation, imageurl, author);
    }

    //setters

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImageurl(Blob imageurl) {
        this.imageurl = imageurl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedat(long createdat) {
        this.createdat = System.currentTimeMillis();
    }

    public void setFormattedCreatedAt(){
        Date date = new Date(this.createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }

    //Getters

    public String getHeadline() {
        return headline;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getAuthor() {
        return author;
    }

    public String getFormattedCreatedAt() {
        Date date = new Date(createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }

    public Blob getImageurl() {
        return imageurl;
    }

    public int getId() {
        return id;
    }

    public long getCreatedat() {
        return createdat;
    }


}
