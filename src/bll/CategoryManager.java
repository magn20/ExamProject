package bll;

import be.Category;
import dal.db.CategoryDAO;

import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    CategoryDAO categoryDAO;

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
