package dao;

import model.Articles;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oArticlesDao implements ArticlesDao {

    private final Sql2o sql2o;
    public Sql2oArticlesDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Articles articles) {
        String sql = "INSERT INTO articles (headline, documentation, imgurl, author, createdat, formattedCreatedAt) VALUES (:headline, :documentation, :imgurl, :author, :createdat, :formattedCreatedAt)"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(articles)
                    .executeUpdate()
                    .getKey();
            articles.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Articles> getAll() {
        try (Connection con = sql2o.open()) {
            System.out.println(con.createQuery("SELECT * FROM articles")
                    .executeAndFetch(Articles.class));
            return con.createQuery("SELECT * FROM articles")
                    .executeAndFetch(Articles.class);
        }
    }



    @Override
    public void clearAll() {
        String sql = "DELETE from articles";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
