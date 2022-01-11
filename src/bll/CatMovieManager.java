package bll;

import be.Category;
import be.Movie;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.CatMovieDAO;
import dal.db.CategoryDAO;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CatMovieManager {

    CatMovieDAO catMovieDAO;

    private DatabaseConnector connector;
    {
        try {
            connector = new DatabaseConnector();
            catMovieDAO = new CatMovieDAO(connector.getConnection());
        } catch (SQLServerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void removeMovieFromCategory(Movie movie, Category category) throws SQLException {
        catMovieDAO.removeMovieFromCategory(movie,category);
    }

    public void addMovieToCategory(Movie movie, Category category) throws SQLException {
        catMovieDAO.addMovieToCategory(movie,category);
    }

    public List<Movie> getMoviesFromCategory(int categoryId) throws SQLException {
        return catMovieDAO.getMoviesFromCategory(categoryId);
    }
    public List<Category> getAllCategoriesFromOneMovie(Movie movie) throws SQLException {
        return catMovieDAO.getAllCategoriesForOneMovie(movie);
    }


}
