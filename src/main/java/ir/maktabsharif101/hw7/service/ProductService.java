package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
