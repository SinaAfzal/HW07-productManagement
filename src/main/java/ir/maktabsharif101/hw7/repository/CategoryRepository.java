package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.Category;

import java.sql.*;

public class CategoryRepository {
    private final Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    public Category save(Category category) throws SQLException {
        String query = "INSERT INTO category (categoryname, description) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, category.getCategoryName());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        category.setId(resultSet.getInt(1));
        return category;
    }

    public boolean doesExist(Category category) throws SQLException {
        String query = "SELECT categoryname FROM category WHERE categoryname=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, category.getCategoryName());
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet.next();
    }


}
