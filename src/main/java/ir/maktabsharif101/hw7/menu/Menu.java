package ir.maktabsharif101.hw7.menu;

import ir.maktabsharif101.hw7.entities.User;
import ir.maktabsharif101.hw7.service.*;
import ir.maktabsharif101.hw7.utility.Validation;

import javax.sound.midi.SysexMessage;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ShareHolderService shareHolderService;
    private final UserService userService;

    public Menu(BrandService brandService,
                CategoryService categoryService,
                ProductService productService,
                UserService userService,
                ShareHolderService shareHolderService) {
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.shareHolderService = shareHolderService;
    }

    Scanner scanner = new Scanner(System.in);

    public void homePage() throws SQLException {
        boolean homePageIsActive = true;
        while (homePageIsActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork7-Maktab101               |");
            System.out.println("|      Product/Brand/Category Management system    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Sign Up");
            System.out.println("2- Sign in");
            System.out.println("3- Exit");

            String input = scanner.next();

            switch (input) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> homePageIsActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.next();
                    System.out.println("**********  Press any key to continue   **********");
                }
            }
        }
    }

    public void register() throws SQLException {
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter your Email: ");
        String email = null;
        boolean invalidEmail = true;
        while (invalidEmail) {
            email = scanner.nextLine();
            if (Validation.isEmailValid(email)) {
                invalidEmail = false;
            } else {
                System.out.println("Enter a valid email!");
            }
        }

        System.out.print("Enter your password: ");
        String password = null;
        boolean invalidPassword = true;
        while (invalidPassword) {
            password = scanner.nextLine();
            if (Validation.isPasswordValid(password)) {
                invalidPassword = false;
            } else {
                System.out.println("Enter a valid password!");
            }
        }
        User user = new User();
        user.setFullName(fullName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        userService.save(user);
    }

    public void login() throws SQLException {
        System.out.print("Username: ");
        String userName = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.login(userName, password);

        if (user != null) {
            mainMenu();
        }
    }

    public void mainMenu() throws SQLException {
        boolean mainMenuIsActive = true;
        while (mainMenuIsActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork7-Maktab101               |");
            System.out.println("|      Product/Brand/Category Management system    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("|                     MAIN MENU                    |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- User operations");
            System.out.println("2- Category operations");
            System.out.println("3- Brand operations");
            System.out.println("4- Shareholder operations");
            System.out.println("5- Product operations");
            System.out.println("6- exit");

            String input = scanner.next();

            switch (input) {
                case "1" -> userMenu();
                case "2" -> categoryMenu();
                case "3" -> brandMenu();
                case "4" -> shareholderMenu();
                case "5" -> productMenu();
                case "6" -> mainMenuIsActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.next();
                    System.out.println("**********  Press any key to continue   **********");
                }
            }
        }
    }

    public void userMenu() {
    }
    public void categoryMenu(){
    }
    public void brandMenu(){
    }
    public void shareholderMenu(){}
    public void productMenu(){}

}
