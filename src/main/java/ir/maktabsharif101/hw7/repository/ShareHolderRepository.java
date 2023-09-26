package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.ShareHolder;

import java.sql.*;

public class ShareHolderRepository {
    private final Connection connection;
    public ShareHolderRepository(Connection connection){
        this.connection=connection;
    }
    public ShareHolder save(ShareHolder shareHolder) throws SQLException {
        String query="INSERT INTO shareholder (shareholdername, phonenumber, nationalcode) VALUES (?,?,?)";
        PreparedStatement preparedStatement=connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,shareHolder.getShareHolderName());
        preparedStatement.setString(2,shareHolder.getPhoneNumber());
        preparedStatement.setString(3,shareHolder.getNationalCode());
        preparedStatement.executeUpdate();
        ResultSet resultSet=preparedStatement.getGeneratedKeys();
        resultSet.next();
        shareHolder.setId(resultSet.getInt(1));
        return shareHolder;
    }

}
