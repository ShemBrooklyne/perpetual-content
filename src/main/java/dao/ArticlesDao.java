package dao;

import model.Articles;

import java.util.List;

public interface ArticlesDao {

    //Create
    void add(Articles articles);

    //Read
    List<Articles> getAll();

    //Update

    //Delete


//// Hard Format: I hope you know what you are doing mate!!!
    void clearAll();
}
