package ir.maktabsharif101.hw7.repository;

import java.sql.Connection;

public class CategoryRepository {
private final Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }


}
