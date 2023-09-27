package ir.maktabsharif101.hw7.service;

import ir.maktabsharif101.hw7.entities.Brand;
import ir.maktabsharif101.hw7.repository.BrandRepository;

import java.sql.SQLException;

public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand save(Brand brand) throws SQLException {
        if (brandRepository.doesExist(brand)) {
            System.out.println("The brand name already exists on the database!");
            return null;
        } else {
            Brand returnedBrand = brandRepository.save(brand);
            if (returnedBrand != null) {
                System.out.println("ID=" + returnedBrand.getId() + " is assigned to the brand and it is stored on the database!");
                return returnedBrand;
            } else {
                System.out.println("OOPS! Something went wrong!");
                return null;
            }
        }
    }


}
