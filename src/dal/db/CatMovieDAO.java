package dal.db;

import be.CatMovie;
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

    /**
     * removes a movie from a certain category.
     * @param movie movie object
     * @param category the category object.
     * @throws SQLException
     */
    //TODO TEST IF THIS WORKS OR GET IT TO WORK.
    public void removeMovieFromCategory(Movie movie, Category category) throws SQLException {

            String sql = "DELETE FROM CatMovie WHERE movieId = (?) AND categoryId = (?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.setInt(2, category.getId());
            preparedStatement.executeUpdate();
    }

    /**
     * adds a movie to a category.
     * @param movie the movie
     * @param category the category
     * @throws SQLException throws a SQLException as error handling.
     */
    public void addMovieToCategory(Movie movie, Category category) throws SQLException {


            String sql = "INSERT INTO CatMovie VALUES (?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setInt(2, movie.getId());
            preparedStatement.executeUpdate();
    }

    /**
     * gives a movies categories.
     * @param categoryId
     * @return
     * @throws SQLException
     */
            //TODO MAKE METOD RETURN THE OTHER WAY AROUND SO YOU GIVE IT A MOVIE ID AND GET ALL THE CATEGORIES ID THIS MOVIE ID IS LINKED WITH.
    public List<Movie> getMoviesFromCategory(int categoryId) throws SQLException {
        List<Movie> moviesInCategory = new ArrayList<>();

            String sql = "SELECT * FROM [examProjectMMA].[dbo].[Movie] INNER JOIN CatMovie ON CatMovie.movieId = Movie.id WHERE categoryId =(?);"; //sql command
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

                    moviesInCategory.add(new Movie( id ,title, personalRating, imdbRating, filelink, lastview));
                }
            }
        
        return moviesInCategory;
    }

    /**
     * gets all categories one movie has
     * @param movie the movie that we looking for categories for
     * @return list of caregories for one movie
     */
    public List<Category> getAllCategoriesForOneMovie(Movie movie) throws SQLException {
        List<Category> categoriesInMovie = new ArrayList<>();


        String sql = "SELECT title FROM Category INNER JOIN CatMovie ON CatMovie.Categoryid = category.id WHERE movieid =(?);"; //sql command
        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, movie.getId());

        //Extract data from DB
        if(preparedStatement.execute()){
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                String title = resultSet.getString("title");

                categoriesInMovie.add(new Category(title));
            }
        }

        return categoriesInMovie;
    }

}
