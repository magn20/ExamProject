package gui.model;

import be.Movie;
import bll.MovieManager;

import java.util.List;

public class MovieModel {
MovieManager movieManager;

    public List<Movie> getMovies() {
        return movieManager.getMovies();
    }

    public Movie createMovie(String title, float personalRating, float imdbRating, String filelink, String lastview) {
        return movieManager.createMovie(new Movie(title,personalRating,imdbRating,filelink,lastview));
    }

    public void updateMovie(Movie movie) throws Exception {
        movieManager.updateMovie(movie);
    }

    public boolean deleteMovie(Movie movieDelete){
        return movieManager.deleteMovie(movieDelete);
    }
}
