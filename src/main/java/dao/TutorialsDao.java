package dao;

import model.Tutorials;

import java.util.List;

public interface TutorialsDao {

    //create
    void add(Tutorials tutorials);

    //Read
    List<Tutorials> getAll();

    //Update

    //Delete
    //// Hard Reset: I hope you KNOW what you are doing mate!!!!
    void clearAll();
}
