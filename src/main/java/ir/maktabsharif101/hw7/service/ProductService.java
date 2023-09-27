package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.entities.Product;
import ir.maktabsharif101.hw7.repository.ProductRepository;

import java.sql.SQLException;

public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    public Product save(Product product) throws SQLException {
        if (brandService.listAllBrands().length < 1 || categoryService.listAllCategories().length < 1) {
            System.out.println("For creating a product, at least one category and one brand should be available on database!");
            System.out.println("Unable to save new product!");
            return null;
        } else {
            Product returnedProduct = productRepository.save(product);
            if (returnedProduct != null) {
                System.out.println("ID=" + returnedProduct.getId() + " is assigned to the product and it is stored on database successfully!");
                return returnedProduct;
            } else {
                System.out.println("OOPS! Something went wrong!");
                return null;
            }
        }
    }

    public int updateProductName(String newProductName, int id) throws SQLException {
        if (productRepository.doesExist(id)) {
            int serverEcho = productRepository.updateProductName(newProductName, id);
            if (serverEcho > 0)
                System.out.println("Product name was changed successfully!");
            else
                System.out.println("OOPS! something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Invalid product id!");
            return -1;
        }
    }


}
