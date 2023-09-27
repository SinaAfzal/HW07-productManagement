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

    public int updateCategoryName(String newCategoryName,int id) throws SQLException {
        String query="UPDATE category SET categoryname=? WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newCategoryName);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int updateCategoryDescription(String newCategoryDescription,int id) throws SQLException{
        String query="UPDATE category SET description=? WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newCategoryDescription);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int countAllCategories() throws SQLException {
        String query="SELECT count(*) FROM category";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }





}
