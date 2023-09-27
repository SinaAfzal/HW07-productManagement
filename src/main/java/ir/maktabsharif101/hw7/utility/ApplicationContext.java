package ir.maktabsharif101.hw7.utility;

import ir.maktabsharif101.hw7.connection.JDBCConnection;
import ir.maktabsharif101.hw7.menu.Menu;
import ir.maktabsharif101.hw7.repository.*;
import ir.maktabsharif101.hw7.service.*;

import java.sql.Connection;

public class ApplicationContext {
    private static final Connection CONNECTION;
    private static final UserRepository USER_REPOSITORY;
    private static final CategoryRepository CATEGORY_REPOSITORY;
    private static final BrandRepository BRAND_REPOSITORY;
    private static final ProductRepository PRODUCT_REPOSITORY;
    private static final ShareHolderRepository SHARE_HOLDER_REPOSITORY;
    private static final BrandService BRAND_SERVICE;
    private static final CategoryService CATEGORY_SERVICE;
    private static final ProductService PRODUCT_SERVICE;
    private static final ShareHolderService SHARE_HOLDER_SERVICE;
    private static final UserService USER_SERVICE;
    private static final Menu MENU;
    static{
        CONNECTION= JDBCConnection.getConnection();
        USER_REPOSITORY=new UserRepository(CONNECTION);
        CATEGORY_REPOSITORY=new CategoryRepository(CONNECTION);
        BRAND_REPOSITORY=new BrandRepository(CONNECTION);
        PRODUCT_REPOSITORY=new ProductRepository(CONNECTION);
        SHARE_HOLDER_REPOSITORY=new ShareHolderRepository(CONNECTION);

        BRAND_SERVICE=new BrandService(BRAND_REPOSITORY);
        CATEGORY_SERVICE=new CategoryService((CATEGORY_REPOSITORY));
        PRODUCT_SERVICE=new ProductService(PRODUCT_REPOSITORY, CATEGORY_SERVICE, BRAND_SERVICE);
        SHARE_HOLDER_SERVICE=new ShareHolderService(SHARE_HOLDER_REPOSITORY);
        USER_SERVICE=new UserService(USER_REPOSITORY);
        MENU=new Menu(BRAND_SERVICE,CATEGORY_SERVICE,PRODUCT_SERVICE,USER_SERVICE,SHARE_HOLDER_SERVICE);
    }
}
