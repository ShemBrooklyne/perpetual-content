import com.google.gson.Gson;
import dao.Sql2oArticlesDao;
import dao.Sql2oTutorialsDao;
import exceptions.ApiExceptions;
import model.Articles;
import model.Tutorials;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {


        port(getHerokuAssignedPort());
        staticFileLocation("/public");


        Sql2oArticlesDao ArticlesDao;
        Sql2oTutorialsDao TutorialsDao;
        Connection conn;
        Gson gson = new Gson();


        //Initializing h2 database. For test env
//        String connectionString = "jdbc:h2:~/perpetual-content.db;INIT=RUNSCRIPT from 'classpath:DB/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");

        //Initializing postgreSQL database. For production env
//        String connectionString = "jdbc:postgresql://localhost:5432/perpetual_content"; //connect to newsportal, not newsportal_test!
//        Sql2o sql2o = new Sql2o(connectionString, "access", "Access");  //Ubuntu Sql2o sql2o = new Sql2o(connectionString, "user", "1234");

        //Deployed database
        String connectionString = "jdbc:postgresql://ec2-54-236-146-234.compute-1.amazonaws.com:5432/d5fi86916f9edp"; //!
        Sql2o sql2o = new Sql2o(connectionString, "gphhqfpkfzorpz", "df1fa768984e0a5c2c29bda4695924a22ae939ab38b346e2c42ad56505d27d65"); //!

        ArticlesDao = new Sql2oArticlesDao(sql2o);
        TutorialsDao = new Sql2oTutorialsDao(sql2o);
        conn = sql2o.open();

        // Welcome screen Json format
        get("/", "application/json", (req, res) ->
                "{\"message\":\"Hello there, please see perpetual-content documentation to have correct routes on the perpetual API.\"}");

        // POST Requests to the sqlDB

        // Add data into Articles table
        post("articles/new", "application/json", ((request, response) -> {
            Articles articles = gson.fromJson(request.body(), Articles.class);
            ArticlesDao.add(articles);
            response.status(201);
            return gson.toJson(articles);
        }));

        // Add data into Tutorials table
        post("tutorials/new", "application/json", ((request, response) -> {
            Tutorials tutorials = gson.fromJson(request.body(), Tutorials.class);
            TutorialsDao.add(tutorials);
            response.status(201);
            return gson.toJson(tutorials);
        }));

        // GET Requests from the sqlDB

        // Requests data from Articles table
        get("/articles", "application/json", ((request, response) -> {
            System.out.println(ArticlesDao.getAll());
            return gson.toJson(ArticlesDao.getAll());
        }));

        // Request data from Tutorials table
        get("/tutorials", "application/json", ((request, response) -> {
            System.out.println(TutorialsDao.getAll());
            return gson.toJson(TutorialsDao.getAll());
        }));

        //FILTERS
        exception(ApiExceptions.class, (exc, req, res) -> {
            ApiExceptions err = exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json"); //after does not run in case of an exception.
            res.status(err.getStatusCode()); //set the status
            res.body(gson.toJson(jsonMap));  //set the output.
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
}
