package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.repository.CategoryRepository;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
