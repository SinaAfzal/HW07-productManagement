package ir.maktabsharif101.hw7;


import ir.maktabsharif101.hw7.menu.Menu;
import ir.maktabsharif101.hw7.utility.ApplicationContext;

import java.sql.SQLException;

public class HW7Application {

    public static void main(String[] args) throws SQLException {
        Menu menu=ApplicationContext.getMenu();
        menu.homePage();

    }
}
