package ir.maktabsharif101.hw7.repository;

import java.sql.Connection;

public class ProductRepository {
    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }
}
