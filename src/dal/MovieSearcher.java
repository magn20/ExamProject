package dal;
import be.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher implements iMovieSearcher {
    /**
     * Searches through song list, to find a song that matches the key word
     *
     * @param searchBase the list of song it should search through
     * @param query the search key word
     * @return a list of songs that fit, the key word
     */
    public ObservableList<Movie> search(List<Movie> searchBase, String query) {
        ObservableList<Movie> searchResult = FXCollections.observableArrayList();

        for (Movie movie : searchBase) {
            if(compareToMovieTitle(movie, query) || compareToCategory(movie, query))
            {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    /**
     * Compare keyword to the songs title
     *
     * @param movie
     * @param query
     * @return true if keyword match title
     */


    public boolean compareToMovieTitle(Movie movie, String query) {
        return movie.getTitle().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Compare keyword to the songs artist
     *
     * @param movie
     * @param query
     * @return true if keyword match artist
     */
    public boolean compareToCategory(Movie movie, String query) {
        return movie.getCategories().toLowerCase().contains(query.toLowerCase());
    }
}
