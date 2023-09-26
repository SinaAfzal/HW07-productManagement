package ir.maktabsharif101.hw7.utility;

import ir.maktabsharif101.hw7.connection.JDBCConnection;
import ir.maktabsharif101.hw7.repository.CategoryRepository;
import ir.maktabsharif101.hw7.repository.UserRepository;

import java.sql.Connection;

public class ApplicationContext {
    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final CategoryRepository CATEGORY_REPOSITORY;
    static{
        CONNECTION= JDBCConnection.getConnection();
        USER_REPOSITORY=new UserRepository(CONNECTION);
        CATEGORY_REPOSITORY=new CategoryRepository(CONNECTION);
    }
}
