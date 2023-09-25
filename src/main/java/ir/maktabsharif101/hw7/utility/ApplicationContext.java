package ir.maktabsharif101.hw7.utility;

import ir.maktabsharif101.hw7.connection.JDBCConnection;

import java.sql.Connection;

public class ApplicationContext {
    private static final Connection CONNECTION;
    static{
        CONNECTION= JDBCConnection.getConnection();
    }
}
