package dal.Interfaces;

import be.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategory {
    public List<Category> getCategories();
    public Category addCategory(String title);
    public void updateCategory(Category category) throws SQLException;
    public void deleteCategory(Category category) throws SQLException;

    }
