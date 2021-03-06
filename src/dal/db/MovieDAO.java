package dal.db;

import be.Movie;
import dal.Interfaces.IMovie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovie {
    private Connection con;

    public MovieDAO(Connection connection) {
        con = connection;
    }

    /**
     *  gives a list of all movies in database.
     * @return all the movies from the database.
     */
    public List<Movie> getMovies() {
        List<Movie> allMovies = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM [examProjectMMA].[dbo].[Movie]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) {
                String title = rs.getString("title");

                Float personalRating = rs.getFloat("personalRating");

                Float imdbRating = rs.getFloat("imdbRating");

                String filelink = rs.getString("filelink");

                String lastview = rs.getString("lastview");

                int id = rs.getInt("id");
                allMovies.add(new Movie(id, title, personalRating, imdbRating, filelink, lastview));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allMovies;
    }

    /**
     * creates a movie in the database.
     * @param title the title of the movie.
     * @param personalRating users own rating of the movie.
     * @param imdbRating imdbRating from imdb website.
     * @param filelink the path to the movie on users computer.
     * @param lastview last date the movies was looked at.
     * @return
     */
    public Movie createMovie(String title, Float personalRating, Float imdbRating, String filelink, String lastview) {

        int insertedId = -1;
        try{
            String sqlStatement = "INSERT INTO Movie (title,personalRating,imdbRating,filelink,lastview) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,title);
            statement.setFloat(2,personalRating);
            statement.setFloat(3,imdbRating);
            statement.setString(4,filelink);
            statement.setString(5,lastview);
            statement.execute();
            ResultSet rs =statement.getGeneratedKeys();
            rs.next();
            insertedId = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Movie(insertedId, title,personalRating , imdbRating, filelink, lastview);
    }

    /**
     * updates the movie information in the database.
     * @param movie movie object that needs to be updated.
     * @throws Exception
     */
    public void updateMovie(Movie movie) throws Exception {

            String sql = "UPDATE Movie SET title = ?, personalRating=?, imdbRating=?, filelink=?, lastview=? WHERE Id=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setFloat(2, movie.getPersonalRating());
            preparedStatement.setFloat(3, movie.getImdbRating());
            preparedStatement.setString(4, movie.getFilelink());
            preparedStatement.setString(5, movie.getLastview());
            preparedStatement.setInt(6, movie.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows != 1) {

            }

        }

    /**
     * deletes a movie in the database.
     * @param movieDelete movie object that we want to delete from database.
     * @return
     */
    public boolean deleteMovie(Movie movieDelete) {
        try{
            String sqlStatement = "DELETE FROM Movie WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sqlStatement);
            statement.setInt(1,movieDelete.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



