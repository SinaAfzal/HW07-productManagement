package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.User;

import java.sql.*;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public User save(User user) throws SQLException {
        String query = "INSERT INTO users (fullname, username, email, password) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getFullName());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        return user;
    }

    public boolean doesExist(User user) throws SQLException {
        String query = "SELECT username FROM users WHERE username=? OR email ILIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public User login(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setFullName(resultSet.getString(2));
            user.setUserName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));

            return user;
        }
        return null;
    }

    public int updateFullName(String newFullName, int id) throws SQLException {
        String query = "UPDATE users SET fullname=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newFullName);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }


}
