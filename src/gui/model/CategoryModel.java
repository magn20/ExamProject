package gui.model;

import be.Category;
import bll.CategoryManager;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    CategoryManager categoryManager = new CategoryManager();

    public List<Category> getCategories() {
        return categoryManager.getCategories();
    }

    public Category addCategory(String title) {
        return categoryManager.addCategory(title);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryManager.updateCategory(category);
    }

    public void deleteCategory(Category category) throws SQLException {
        categoryManager.deleteCategory(category);
    }
}
