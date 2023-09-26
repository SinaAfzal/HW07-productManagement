package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.Brand;

import java.sql.*;

public class BrandRepository {
    private final Connection connection;

    public BrandRepository(Connection connection) {
        this.connection = connection;
    }

    public Brand save(Brand brand) throws SQLException {
        String query="INSERT INTO brand (brandname, website, description) VALUES (?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,brand.getBrandName());
        preparedStatement.setString(2,brand.getWebsite());
        preparedStatement.setString(3,brand.getDescription());
        preparedStatement.executeUpdate();
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        resultSet.next();
        brand.setId(resultSet.getInt(1));
        return brand;
    }

    public boolean doesExist(Brand brand) throws SQLException {
        String query="SELECT brandname FROM brand WHERE brandname=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,brand.getBrandName());
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet.next();
    }
}
