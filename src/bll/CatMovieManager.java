package bll;

import be.Category;
import be.Movie;
import dal.db.CatMovieDAO;

import java.sql.SQLException;
import java.util.List;

public class CatMovieManager {

    CatMovieDAO catMovieDAO;

    public void removeMovieFromCategory(Movie movie, Category category) throws SQLException {
        catMovieDAO.removeMovieFromCategory(movie,category);
    }

    public void addMovieToCategory(Movie movie, Category category) throws SQLException {
        catMovieDAO.addMovieToCategory(movie,category);
    }

    public List<Movie> getMoviesFromCategory(int categoryId) throws SQLException {
        return catMovieDAO.getMoviesFromCategory(categoryId);
    }


}
