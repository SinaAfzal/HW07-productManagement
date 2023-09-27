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
        if (brandRepository.doesExist(brand.getBrandName())) {
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

    public int updateBrandName(String newBrandName,String OldBrandName) throws SQLException {
        if (brandRepository.doesExist(OldBrandName)){
            int serverEcho=brandRepository.updateBrandName(newBrandName,OldBrandName);
            if (serverEcho>0)
                System.out.println("Brand name was changed from "+OldBrandName+" to "+newBrandName+" successfully!");
            else
                System.out.println("OOPS! something went wrong!");
            return serverEcho;
        } else{
            System.out.println(OldBrandName+" was not found on the database!");
            return -1;
        }
    }




}
