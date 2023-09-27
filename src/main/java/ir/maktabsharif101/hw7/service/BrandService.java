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

    public int updateBrandName(String newBrandName, String OldBrandName) throws SQLException {
        if (brandRepository.doesExist(OldBrandName)) {
            int serverEcho = brandRepository.updateBrandName(newBrandName, OldBrandName);
            if (serverEcho > 0)
                System.out.println("Brand name was changed from " + OldBrandName + " to " + newBrandName + " successfully!");
            else
                System.out.println("OOPS! something went wrong!");
            return serverEcho;
        } else {
            System.out.println(OldBrandName + " was not found on the database!");
            return -1;
        }
    }

    public int updateBrandDescription(String newBrandDescription, int brandID) throws SQLException {
        if (brandRepository.doesExist(brandID)) {
            int serverEcho = brandRepository.updateBrandDescription(newBrandDescription, brandID);
            if (serverEcho > 0)
                System.out.println("Brand description is updated successfully!");
            else
                System.out.println("OOPS! something went wrong!");
            return serverEcho;
        } else {
            System.out.println("Brand ID is not valid!");
            return -1;
        }
    }

    public Brand load(int id) throws SQLException {
        if (brandRepository.doesExist(id)) {
            Brand loadedBrand = brandRepository.load(id);
            if (loadedBrand == null)
                System.out.println("OOPS! Brand was found but could not be loaded!");
            else {
                System.out.println("Brand with ID=" + id + " is loaded successfully!:");
                System.out.println("   -Brand name: " + loadedBrand.getBrandName());
                System.out.println("   -Brand website: " + loadedBrand.getWebsite());
                System.out.println("   -Brand description: " + loadedBrand.getDescription());
                System.out.println("   -Brand shareholders: ");
                if (loadedBrand.getShareHolderIds().length > 0) {
                    for (int i = 0; i < loadedBrand.getShareHolderIds().length; i++) {
                        if (i != loadedBrand.getShareHolderIds().length - 1)
                            System.out.print(loadedBrand.getShareHolderIds()[i] + ",");
                        else
                            System.out.print(loadedBrand.getShareHolderIds()[i]);
                    }
                } else {
                    System.out.print("-");
                }
            }
            return loadedBrand;
        } else {
            System.out.println("Invalid brand id!");
            return null;
        }
    }

    public Brand[] listAllBrands() throws SQLException {
        Brand[] brands = brandRepository.listAllBrands();
        if (brands.length > 0) {
            System.out.println("# List of available brands on database: ");
            System.out.println("-------------------------");
            System.out.println("     ID    |   Brand name");
            System.out.println("-------------------------");

            for (int i = 0; i < brands.length; i++) {
                System.out.println("|     " + brands[i].getId() + "    " + "    " + brands[i].getBrandName());
            }
            System.out.println("-------------------------");
        } else {
            System.out.println("No brands available!");
        }
        return brands;
    }

    public void delete(int id) throws SQLException {
        if (brandRepository.doesExist(id)) {
            brandRepository.delete(id);
            System.out.println("Brand was deleted!");
        } else {
            System.out.println("Invalid brand ID!");
        }
    }
}
