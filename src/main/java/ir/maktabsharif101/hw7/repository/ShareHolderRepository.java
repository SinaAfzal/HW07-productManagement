package ir.maktabsharif101.hw7.repository;

import ir.maktabsharif101.hw7.entities.ShareHolder;

import java.sql.*;

public class ShareHolderRepository {
    private final Connection connection;

    public ShareHolderRepository(Connection connection) {
        this.connection = connection;
    }

    public ShareHolder save(ShareHolder shareHolder) throws SQLException {
        String query = "INSERT INTO shareholder (shareholdername, phonenumber, nationalcode) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, shareHolder.getShareHolderName());
        preparedStatement.setString(2, shareHolder.getPhoneNumber());
        preparedStatement.setString(3, shareHolder.getNationalCode());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        shareHolder.setId(resultSet.getInt(1));
        return shareHolder;
    }

    public boolean doesExist(ShareHolder shareHolder) throws SQLException {
        String query = "SELECT nationalcode FROM shareholder WHERE nationalcode=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, shareHolder.getNationalCode());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public int updateShareHolderName(String newShareHolderName, int id) throws SQLException {
        String query = "UPDATE shareholder SET shareholdername=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newShareHolderName);
        preparedStatement.setInt(2, id);
        return preparedStatement.executeUpdate();
    }

    public int updatePhoneNumber(String newPhoneNumber, int id) throws SQLException {
        String query = "UPDATE shareholder SET phonenumber=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newPhoneNumber);
        preparedStatement.setInt(2, id);
        return preparedStatement.executeUpdate();
    }

    public int updateNationalCode(String newNationalCode, int id) throws SQLException {
        String query = "UPDATE shareholder SET nationalcode=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, newNationalCode);
        preparedStatement.setInt(2, id);
        return preparedStatement.executeUpdate();
    }

    public int countBrandsOfShareHolder(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM shareholder_brand WHERE shareholderid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    public ShareHolder loadShareHolder(int id) throws SQLException {
        String query = "SELECT * FROM shareholder WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ShareHolder shareHolder = new ShareHolder();
        while (resultSet.next()) {
            shareHolder.setId(id);
            shareHolder.setShareHolderName(resultSet.getString("shareholdername"));
            shareHolder.setPhoneNumber(resultSet.getString("phonenumber"));
            shareHolder.setNationalCode(resultSet.getString("nationalcode"));
        }
        int numberOfFoundBrands = countBrandsOfShareHolder(id);
        if (numberOfFoundBrands > 0) {
            String relatedBrandFinderQuery = "SELECT * FROM shareholder_brand WHERE shareholderid=?";
            PreparedStatement preparedStatementRelatedBrandFinder = connection.prepareStatement(relatedBrandFinderQuery);
            preparedStatementRelatedBrandFinder.setInt(1, id);
            ResultSet resultSetFoundRelatedBrands = preparedStatementRelatedBrandFinder.executeQuery();
            int[] brandIDs = new int[numberOfFoundBrands];
            int counter = 0;
            while (resultSetFoundRelatedBrands.next()) {
                brandIDs[counter++] = resultSet.getInt("brandid");
            }
            shareHolder.setBrandIds(brandIDs);
        }
        return shareHolder;
    }

    public int buyBrandShares(int shareholderId, int brandId) throws SQLException {
        String query = "INSERT INTO shareholder_brand (brandid, shareholderid) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, brandId);
        preparedStatement.setInt(2, shareholderId);
        return preparedStatement.executeUpdate();
    }
    public int countAllShareHolders() throws SQLException {
        String query="SELECT count(*) FROM shareholder";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }
    public ShareHolder[] listAllShareHolders() throws SQLException{
        String query="SELECT id FROM shareholder";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        ShareHolder[] shareHolders=new ShareHolder[countAllShareHolders()];
        int counter=0;
        while (resultSet.next()){
            shareHolders[counter++]=loadShareHolder(resultSet.getInt(1));
        }
        return shareHolders;
    }

    public void delete(int id) throws SQLException {
        String queryShareHolderTable="DELETE FROM shareholder WHERE id=?";
        PreparedStatement preparedStatement=connection.prepareStatement(queryShareHolderTable);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

        String queryShareHolder_Brand_Table="DELETE FROM shareholder_brand WHERE shareholderid=?";
        PreparedStatement preparedStatement_ShareHolder_Brand_Relation=connection.prepareStatement(queryShareHolder_Brand_Table);
        preparedStatement_ShareHolder_Brand_Relation.setInt(1,id);
        preparedStatement_ShareHolder_Brand_Relation.executeUpdate();
    }

}
