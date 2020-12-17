import com.google.gson.Gson;
import dao.Sql2oArticlesDao;
import dao.Sql2oTutorialsDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.get;

public class App {

    public static void main(String[] args) {
        Sql2oArticlesDao ArticlesDao;
        Sql2oTutorialsDao TutorialsDao;
        Connection conn;
        Gson gson = new Gson();

        //Initializing h2 database. For test env
        String connectionString = "jdbc:h2:~/perpetual-content.db;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        ArticlesDao = new Sql2oArticlesDao(sql2o);
        TutorialsDao = new Sql2oTutorialsDao(sql2o);
        conn = sql2o.open();

        get("/", "application/json", (req, res) ->
                "{\"message\":\"Hello great Farmer! Welcome On-Board in the pursuits of solving world hunger. \n ZERO WORLD HUNGER!!!\"}");
    }
}
