package gui.model;

import be.Category;
import be.Movie;
import bll.CatMovieManager;

import java.sql.SQLException;
import java.util.List;

public class CatMovieModel {

    CatMovieManager catMovieManager;

    public  CatMovieModel(){
        catMovieManager = new CatMovieManager();
    }
    public void removeMovieFromCategory(Movie movie, Category category) throws SQLException {
        catMovieManager.removeMovieFromCategory(movie,category);
    }

    public void addMovieToCategory(Movie movie, Category category) throws SQLException {
        catMovieManager.addMovieToCategory(movie,category);
    }

    public List<Movie> getMoviesFromCategory(int categoryId) throws SQLException {
        return catMovieManager.getMoviesFromCategory(categoryId);
    }
}
