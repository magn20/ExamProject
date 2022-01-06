package bll;

import be.Movie;
import dal.db.MovieDAO;

import java.util.List;

public class MovieManager {

    MovieDAO moviedao;

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
        return moviedao.createMovie(movie.getTitle(),movie.getPersonalRating(),movie.getImdbRating(),movie.getFilelink(),movie.getLastview());
    }



}
