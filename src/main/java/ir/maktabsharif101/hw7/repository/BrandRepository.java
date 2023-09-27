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

}
