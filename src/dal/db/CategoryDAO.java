package dal.db;

import be.Category;
import dal.Interfaces.ICategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategory {
    private Connection con;

    public CategoryDAO (Connection connection) {
        con = connection;

    }

    public List<Category> getCategories() {
        List<Category> allCategories = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM [examProjectMMA].[dbo].[Category]";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) {
                String title = rs.getString("title");
                int id = rs.getInt("id");
                allCategories.add(new Category(id, title));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCategories;

    }

    /**
     * adds a category to the database
     * @param title the name of the category
     * @return the category object that was created.
     */
    public Category addCategory(String title) {
        int insertedId = -1;
        try {
            String sqlStatement = "INSERT INTO Category (title) VALUES (?);";
            PreparedStatement statement = con.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            insertedId = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Category category = new Category(insertedId, title);
        return category;

    }

    /**
     * used for updating the category name.
     * @param category category object that should be updated.
     * @throws SQLException
     */
    public void updateCategory(Category category) throws SQLException {

            String sql = "UPDATE Category SET title=? WHERE Id=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, category.getTitle());
            preparedStatement.setInt(2, category.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {

            }

    }

    /**
     * removes a category from the database
     * @param category object that should be removed from the database.
     * @throws SQLException
     */
    public void deleteCategory(Category category) throws SQLException {

            String sql = "DELETE FROM Category WHERE Id=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, category.getId());
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows != 1){

        }
    }
}
