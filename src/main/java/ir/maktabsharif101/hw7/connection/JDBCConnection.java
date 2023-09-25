package ir.maktabsharif101.hw7.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private final String DATABASE_URL="jdbc:postgresql://localhost:5432/postgres";
    private final String DATABASE_USER="postgres";
    private final String DATABASE_PASSWORD="12369874";
    private Connection connection= DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);

    public JDBCConnection() throws SQLException {
    }

    public Connection getConnection() {
        return connection;
    }
}
