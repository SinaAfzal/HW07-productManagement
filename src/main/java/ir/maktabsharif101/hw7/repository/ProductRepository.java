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

    public boolean doesExist(int id) throws SQLException{
        String query="SELECT productname FROM product WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet=preparedStatement.executeQuery();
        return resultSet.next();
    }

    public int updateProductName(String newProductName,int id) throws SQLException{
        String query="UPDATE product SET productname=? WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setString(1,newProductName);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int updateCategoryId(int newCategoryId, int id) throws SQLException{
        String query="UPDATE product SET categoryid=? WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,newCategoryId);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int updateBrandId(int newBrandId, int id) throws SQLException{
        String query="UPDATE product SET brandid=? WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,newBrandId);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public Product load(int id) throws SQLException {
        String query="SELECT * FROM product WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        Product product=new Product();
        while (resultSet.next()){
            product.setId(id);
            product.setProductName(resultSet.getString(2));
            product.setCreateDate(resultSet.getTimestamp(3));
            product.setCategoryId(resultSet.getInt(4));
            product.setBrandId(resultSet.getInt(5));
        }
        return product;
    }

    public int countAllProducts() throws SQLException{
        String query="SELECT count(*) FROM product";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
    public Product[] listAllProducts() throws SQLException {
        String query="SELECT id FROM product";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        Product[] products=new Product[countAllProducts()];
        int counter=0;
        while (resultSet.next()){
            products[counter++]=load(resultSet.getInt(1));
        }
        return products;
    }
}
