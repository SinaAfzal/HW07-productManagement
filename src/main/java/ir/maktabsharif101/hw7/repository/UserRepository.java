package ir.maktabsharif101.hw7.repository;

import java.sql.Connection;

public class UserRepository {
    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

}
