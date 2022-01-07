package bll;

import be.Category;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.CategoryDAO;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    private DatabaseConnector connector;
    CategoryDAO categoryDAO;
    {
        try {
            connector = new DatabaseConnector();
            categoryDAO = new CategoryDAO(connector.getConnection());
        } catch (SQLServerException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategories(){
        return categoryDAO.getCategories();
    }

    public Category addCategory(String title){
        return categoryDAO.addCategory(title);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryDAO.updateCategory(category);
    }

    public void deleteCategory(Category category) throws SQLException {
        categoryDAO.deleteCategory(category);
    }
}
