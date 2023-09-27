package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.repository.BrandRepository;

public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
}
