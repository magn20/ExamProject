package dal.Interfaces;

import be.Movie;

import java.util.List;

public interface IMovie {
    public List<Movie> getMovies();
    public Movie createMovie(String title, Float personalRating, Float imdbRating, String filelink, String lastview);
    public void updateMovie(Movie movie) throws Exception;
    public boolean deleteMovie(Movie movieDelete);

}
