package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.Product;

import java.sql.*;

public class ProductRepository {
    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }
    public Product save(Product product) throws SQLException {
        String query="INSERT INTO product (productname, createdate, categoryid, brandid) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,product.getProductName());
        preparedStatement.setTimestamp(2,product.getCreateDate());
        preparedStatement.setInt(3,product.getCategoryId());
        preparedStatement.setInt(4,product.getBrandId());
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        resultSet.next();
        product.setId(resultSet.getInt(1));
        return product;
    }

}
