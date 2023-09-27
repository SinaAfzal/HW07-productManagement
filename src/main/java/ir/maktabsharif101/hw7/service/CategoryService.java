package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.entities.Category;
import ir.maktabsharif101.hw7.repository.CategoryRepository;

import java.sql.SQLException;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) throws SQLException {
        if (categoryRepository.doesExist(category.getCategoryName())) {
            System.out.println("The category name already exists on the database!");
            return null;
        } else {
            Category returnedCategory = categoryRepository.save(category);
            if (returnedCategory != null) {
                System.out.println("ID=" + returnedCategory.getId() + " is assigned to the category and it is stored on database successfully!");
                return returnedCategory;
            } else {
                System.out.println("OOPS! Something went wrong!");
                return null;
            }
        }
    }

    public int updateCategoryName(String newCategoryName, String oldCategoryName) throws SQLException {
        if (categoryRepository.doesExist(oldCategoryName)) {
            int serverEcho = categoryRepository.updateCategoryName(newCategoryName, oldCategoryName);
            if (serverEcho > 0)
                System.out.println("Category name was changed from " + oldCategoryName + " to " + newCategoryName + " successfully!");
            else
                System.out.println("OOPS! something went wrong!");
            return serverEcho;
        } else {
            System.out.println(oldCategoryName + " was not found on the database!");
            return -1;
        }
    }

    public int updateCategoryDescription(String newCategoryDescription, int id) throws SQLException {
        if (categoryRepository.doesExist(id)) {
            int serverEcho = categoryRepository.updateCategoryDescription(newCategoryDescription, id);
            if (serverEcho > 0)
                System.out.println("Category description is updated successfully!");
            else
                System.out.println("OOPS! something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Category ID is not valid!");
            return -1;
        }
    }

    public Category load(int id) throws SQLException {
        if (categoryRepository.doesExist(id)) {
            Category loadedCategory = categoryRepository.load(id);
            if (loadedCategory == null)
                System.out.println("OOPS! Category was found but could not be loaded!");
            else {
                System.out.println("Category with ID=" + id + " is loaded successfully!:");
                System.out.println("   -Category name: " + loadedCategory.getCategoryName());
                System.out.println("   -Category description: " + loadedCategory.getDescription());
            }
            return loadedCategory;
        } else {
            System.out.println("Invalid category id!");
            return null;
        }
    }

    public Category[] listAllCategories() throws SQLException {
        Category[] categories = categoryRepository.listAllCategories();
        if (categories.length > 0) {
            System.out.println("# List of available categories on database: ");
            System.out.println("----------------------------");
            System.out.println("     ID    |   Category name");
            System.out.println("----------------------------");

            for (int i = 0; i < categories.length; i++) {
                System.out.println("|     " + categories[i].getId() + "    " + "    " + categories[i].getCategoryName());
            }
            System.out.println("----------------------------");
        } else {
            System.out.println("No categories available!");
        }
        return categories;
    }

    public void delete(int id) throws SQLException{
        if (categoryRepository.doesExist(id)) {
            categoryRepository.delete(id);
            System.out.println("Category was deleted!");
        } else {
            System.out.println("Invalid category ID!");
        }
    }

}
