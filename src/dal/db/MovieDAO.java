package dal.db;

import be.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private Connection con;

    public MovieDAO(Connection connection) {
        con = connection;
    }

    public List<Movie> getMovies() {
        List<Movie> allMovies = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM [Movie].[dbo].[Movie]";
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



