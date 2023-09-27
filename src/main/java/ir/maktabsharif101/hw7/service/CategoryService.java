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
        if (categoryRepository.doesExist(category.getCategoryName())){
            System.out.println("The category name already exists on the database!");
            return null;
        }else{
            Category returnedCategory=categoryRepository.save(category);
            if (returnedCategory!=null){
                System.out.println("ID="+returnedCategory.getId()+" is assigned to the category and it is stored on database successfully!");
                return returnedCategory;
            }else {
                System.out.println("OOPS! Something went wrong!");
                return null;
            }
        }
    }


}
