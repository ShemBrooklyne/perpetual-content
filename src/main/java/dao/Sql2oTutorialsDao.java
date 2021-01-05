package dao;

import model.Tutorials;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTutorialsDao implements TutorialsDao{

    private final Sql2o sql2o;
    public Sql2oTutorialsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(Tutorials tutorials) {
        String sql = "INSERT INTO tutorials (title, videourl, desc, source, createdat, formattedCreatedAt) VALUES (:title, :videourl, :desc, :source, :createdat, :formattedCreatedAt)"; //if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(tutorials)
                    .executeUpdate()
                    .getKey();
            tutorials.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Tutorials> getAll() {
        try (Connection con = sql2o.open()) {
            System.out.println(con.createQuery("SELECT * FROM tutorials")
                    .executeAndFetch(Tutorials.class));
            return con.createQuery("SELECT * FROM tutorials")
                    .executeAndFetch(Tutorials.class);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from tutorials";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
