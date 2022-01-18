package gui.model;

import be.Movie;
import bll.MovieManager;

import java.util.List;

public class MovieModel {
MovieManager movieManager;

    public MovieModel(){
        movieManager = new MovieManager();
    }

    public List<Movie> getMovies() {
        return movieManager.getMovies();
    }

    public Movie createMovie(Movie movie) {
        return movieManager.createMovie(movie);
    }

    public void updateMovie(Movie movie) throws Exception {
        movieManager.updateMovie(movie);
    }

    public boolean deleteMovie(Movie movieDelete){
        return movieManager.deleteMovie(movieDelete);
    }

}
