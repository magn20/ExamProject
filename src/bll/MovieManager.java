package bll;

import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.CategoryDAO;
import dal.db.DatabaseConnector;
import dal.db.MovieDAO;

import java.io.IOException;
import java.util.List;

public class MovieManager {

    private MovieDAO moviedao;
    private DatabaseConnector connector;
    {
        try {
            connector = new DatabaseConnector();
            moviedao = new MovieDAO(connector.getConnection());
        } catch (SQLServerException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMovies() {
        return moviedao.getMovies();
    }

    public void updateMovie(Movie movie) throws Exception {
        moviedao.updateMovie(movie);
    }

    public boolean deleteMovie(Movie movieDelete){
        return moviedao.deleteMovie(movieDelete);
    }
    public Movie createMovie(Movie movie) {
        System.out.println(movie.getTitle());
        return moviedao.createMovie(movie.getTitle(),movie.getPersonalRating(),movie.getImdbRating(),movie.getFilelink(),movie.getLastview());
    }



}
