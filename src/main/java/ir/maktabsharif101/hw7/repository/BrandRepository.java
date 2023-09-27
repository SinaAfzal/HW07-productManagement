package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.Brand;

import java.sql.*;

public class BrandRepository {
    private final Connection connection;

    public BrandRepository(Connection connection) {
        this.connection = connection;
    }

    public Brand save(Brand brand) throws SQLException {
        String brandTableQuery = "INSERT INTO brand (brandname, website, description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(brandTableQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, brand.getBrandName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        brand.setId(resultSet.getInt(1));

        String shareholder_brand_TableQuery = "INSERT INTO shareholder_brand (brandid, shareholderid) VALUES (?,?)";
        PreparedStatement preparedStatement_brand_shareholder = connection.prepareStatement(shareholder_brand_TableQuery);
        for (int i = 0; i < brand.getShareHolderIds().length; i++) {
            preparedStatement_brand_shareholder.setInt(1, brand.getId());
            preparedStatement_brand_shareholder.setInt(2, brand.getShareHolderIds()[i]);
            preparedStatement_brand_shareholder.addBatch();
        }
        preparedStatement_brand_shareholder.executeBatch();
        return brand;
    }

    public boolean doesExist(Brand brand) throws SQLException {
        String query = "SELECT brandname FROM brand WHERE brandname=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, brand.getBrandName());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public int updateBrandName(String newBrandName, int id) throws SQLException {
        String query = "UPDATE brand SET brandname=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newBrandName);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateBrandDescription(String newBrandDescription, int id) throws SQLException {
        String query = "UPDATE brand SET description=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newBrandDescription);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int countShareHoldersOfBrand(int id) throws SQLException {
        String query = "SELECT * FROM shareholder_brand WHERE brandid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public Brand load(int id) throws SQLException {
        String query = "SELECT * FROM brand WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Brand brand = new Brand();
        while (resultSet.next()) {
            brand.setId(resultSet.getInt(1));
            brand.setBrandName(resultSet.getString(2));
            brand.setWebsite(resultSet.getString(3));
            brand.setDescription(resultSet.getString(4));
        }
        int NumberOfShareHolders = countShareHoldersOfBrand(id);
        if (NumberOfShareHolders > 0) {
            String queryFindShareHolders = "SELECT * FROM shareholder_brand WHERE brandid=?";
            PreparedStatement preparedStatementFindShareHolders = connection.prepareStatement(queryFindShareHolders);
            preparedStatementFindShareHolders.setInt(1, id);
            ResultSet resultSetFoundShareholders = preparedStatementFindShareHolders.executeQuery();
            int[] shareHolderIDs = new int[NumberOfShareHolders];
            int counter = 0;
            while (resultSetFoundShareholders.next()) {
                shareHolderIDs[counter++] = resultSetFoundShareholders.getInt("shareholderid");
            }
            brand.setShareHolderIds(shareHolderIDs);
        }
        return brand;
    }

    public int countAllBrands() throws SQLException {
        String query = "SELECT COUNT(*) FROM brand";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public Brand[] listAllBrands() throws SQLException {
        String query="SELECT * FROM brand";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        Brand[] brands=new Brand[countAllBrands()];
        int counter=0;
        while (resultSet.next()){
            brands[counter++]=load(resultSet.getInt("id"));
        }
        return brands;
    }

}
