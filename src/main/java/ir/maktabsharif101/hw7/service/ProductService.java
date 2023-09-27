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
                System.out.println("OOPS! Something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Invalid product id!");
            return -1;
        }
    }

    public int updateProductBrand(int productID, int newBrandID) throws SQLException {
        if (productRepository.doesExist(productID)) {
            int serverEcho = productRepository.updateBrandId(newBrandID, productID);
            if (serverEcho > 0)
                System.out.println("Product brand was changed successfully!");
            else
                System.out.println("OOPS! Something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Invalid product id!");
            return -1;
        }
    }

    public int updateProductCategory(int productID, int newCategoryID) throws SQLException {
        if (productRepository.doesExist(productID)) {
            int serverEcho = productRepository.updateCategoryId(newCategoryID, productID);
            if (serverEcho > 0)
                System.out.println("Product category was changed successfully!");
            else
                System.out.println("OOPS! Something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Invalid product id!");
            return -1;
        }
    }

    public Product load(int id) throws SQLException {
        if (productRepository.doesExist(id)) {
            Product loadedProduct = productRepository.load(id);
            if (loadedProduct == null)
                System.out.println("OOPS! Product was found but could not be loaded!");
            else {
                System.out.println("Product with ID=" + id + " is loaded successfully!:");
                System.out.println("   -Product name: " + loadedProduct.getProductName());
                System.out.println("   -Product creation date: " + loadedProduct.getCreateDate());
                System.out.println("   -Product category ID: " + loadedProduct.getCategoryId());
                System.out.println("   -Product brand ID: " + loadedProduct.getBrandId());
            }
            return loadedProduct;
        } else {
            System.out.println("Invalid category id!");
            return null;
        }
    }

    public Product[] listAllProducts() throws SQLException {
        Product[] products = productRepository.listAllProducts();
        if (products.length > 0) {
            System.out.println("# List of available products on database: ");
            System.out.println("----------------------------");
            System.out.println("     ID    |   Product name");
            System.out.println("----------------------------");

            for (int i = 0; i < products.length; i++) {
                System.out.println("|     " + products[i].getId() + "    " + "    " + products[i].getProductName());
            }
            System.out.println("----------------------------");
        } else {
            System.out.println("No products available!");
        }
        return products;
    }

    public void delete(int id) throws SQLException {
        if (productRepository.doesExist(id)) {
            productRepository.delete(id);
            System.out.println("Product was deleted!");
        } else {
            System.out.println("Invalid product ID!");
        }
    }


}
