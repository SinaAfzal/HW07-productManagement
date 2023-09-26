package ir.maktabsharif101.hw7.repository;

import java.sql.Connection;

public class ShareHolderRepository {
    private final Connection connection;
    public ShareHolderRepository(Connection connection){
        this.connection=connection;
    }

}
