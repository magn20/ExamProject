package dal.db;

import be.Category;
import be.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO {

    private Connection con;

    public CatMovieDAO (Connection connection) {
        con = connection;

    }

    public void removeMovieFromCategory(Movie movie, Category category) throws SQLException {

            String sql = "DELETE FROM CatMovie WHERE movieId = (?) AND categoryId = (?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
    }

    public void addMovieToCategory(Movie movie, Category category) throws SQLException {

            String sql = "INSERT INTO CatMovie VALUES (?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setInt(2, movie.getId());
            preparedStatement.executeUpdate();
    }
    public List<Movie> getMoviesFromCategory(int categoryId) throws SQLException {
        List<Movie> moviesInCategory = new ArrayList<>();

            String sql = "SELECT id, title, personalRating, imdbRating, filelink, lastview FROM Movie INNER JOIN CatMovie ON CatMovie.movieId = Movie.id WHERE categoryId =(?);"; //sql command
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, categoryId);

            //Extract data from DB
            if(preparedStatement.execute()){
                ResultSet resultSet = preparedStatement.getResultSet();
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    Float personalRating = resultSet.getFloat("personalRating");
                    Float imdbRating = resultSet.getFloat("imdbRating");
                    String filelink = resultSet.getString("filelink");
                    String lastview = resultSet.getString("lastview");

                    moviesInCategory.add(new Movie(id, title, personalRating, imdbRating, filelink, lastview));
                }
            }
        
        return moviesInCategory;
    }

}
