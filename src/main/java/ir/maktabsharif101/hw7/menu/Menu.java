package ir.maktabsharif101.hw7.menu;

import ir.maktabsharif101.hw7.entities.Brand;
import ir.maktabsharif101.hw7.entities.Category;
import ir.maktabsharif101.hw7.entities.ShareHolder;
import ir.maktabsharif101.hw7.entities.User;
import ir.maktabsharif101.hw7.service.*;
import ir.maktabsharif101.hw7.utility.Validation;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ShareHolderService shareHolderService;
    private final UserService userService;
    private User user;

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
                    scanner.next();
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

        user = userService.login(userName, password);

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

    public void userMenu() throws SQLException {
        boolean userMenuIsActive = true;
        while (userMenuIsActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork7-Maktab101               |");
            System.out.println("|      Product/Brand/Category Management system    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("|                     USER MENU                    |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Update full name");
            System.out.println("2- exit");

            String input = scanner.next();

            switch (input) {
                case "1" -> updateUserFullName();
                case "2" -> userMenuIsActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.next();
                    System.out.println("**********  Press any key to continue   **********");
                    scanner.next();
                }
            }
        }
    }

    public void updateUserFullName() throws SQLException {
        System.out.print("Enter your new full name: ");
        String newFullName = scanner.nextLine();
        userService.updateFullName(newFullName, user.getId());
    }

    public void categoryMenu() throws SQLException {
        boolean categoryMenuIsActive = true;
        while (categoryMenuIsActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork7-Maktab101               |");
            System.out.println("|      Product/Brand/Category Management system    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("|                   CATEGORY MENU                  |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Create a new category");
            System.out.println("2- Update category name");
            System.out.println("3- Update category description");
            System.out.println("4- Load a category");
            System.out.println("5- View list of all categories");
            System.out.println("6- Delete a category");
            System.out.println("7- exit");

            String input = scanner.next();

            switch (input) {
                case "1" -> createCategory();
                case "2" -> updateCategoryName();
                case "3" -> updateCategoryDescription();
                case "4" -> loadCategory();
                case "5" -> listAllCategories();
                case "6" -> deleteCategory();
                case "7" -> categoryMenuIsActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.next();
                    System.out.println("**********  Press any key to continue   **********");
                    scanner.next();
                }
            }
        }
    }

    public void createCategory() throws SQLException {
        System.out.println("Category name: ");
        String categoryName = scanner.nextLine();

        System.out.println("Category description: ");
        String categoryDescription = scanner.nextLine();

        Category category = new Category(null, categoryName, categoryDescription);
        categoryService.save(category);
    }

    public void updateCategoryName() throws SQLException {
        System.out.print("Old category name: ");
        String oldCategoryName = scanner.nextLine();

        System.out.print("New category name: ");
        String newCategoryName = scanner.nextLine();

        categoryService.updateCategoryName(newCategoryName, oldCategoryName);
    }

    public void updateCategoryDescription() throws SQLException {
        System.out.print("Category ID: ");
        int id = scanner.nextInt();

        System.out.println("New category description: ");
        String newDescription = scanner.nextLine();

        categoryService.updateCategoryDescription(newDescription, id);
    }

    public void loadCategory() throws SQLException {
        System.out.print("Enter Category ID: ");
        int id = scanner.nextInt();

        categoryService.load(id);
    }

    public void listAllCategories() throws SQLException {
        System.out.println();
        categoryService.listAllCategories();
    }

    public void deleteCategory() throws SQLException {
        System.out.print("Enter Category ID: ");
        int id = scanner.nextInt();

        categoryService.delete(id);
    }

    public void brandMenu() throws SQLException {
        boolean brandMenuIsActive = true;
        while (brandMenuIsActive) {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork7-Maktab101               |");
            System.out.println("|      Product/Brand/Category Management system    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("|                     BRAND MENU                   |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Create a new brand");
            System.out.println("2- Update brand name");
            System.out.println("3- Update brand description");
            System.out.println("4- Load a brand");
            System.out.println("5- View list of all brands");
            System.out.println("6- Delete a brand");
            System.out.println("7- exit");

            String input = scanner.next();

            switch (input) {
                case "1" -> createBrand();
                case "2" -> updateBrandName();
                case "3" -> updateBrandDescription();
                case "4" -> loadBrand();
                case "5" -> listAllBrands();
                case "6" -> deleteBrand();
                case "7" -> brandMenuIsActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.next();
                    System.out.println("**********  Press any key to continue   **********");
                    scanner.next();
                }
            }
        }
    }

    public void createBrand() throws SQLException {
        System.out.println("Brand name: ");
        String brandName = scanner.nextLine();

        System.out.println("Brand website: ");
        String website = null;
        boolean invalidWebsite = true;
        while (invalidWebsite) {
            website = scanner.nextLine();
            if (Validation.isWebsiteValid(website)) {
                invalidWebsite = false;
            } else {
                System.out.println("Enter a valid website!");
            }
        }

        System.out.println("Brand description: ");
        String brandDescription = scanner.nextLine();

        Brand brand = new Brand(null, brandName, website, brandDescription, null);
        brandService.save(brand);
    }

    public void updateBrandName() throws SQLException{
        System.out.print("Old brand name: ");
        String oldBrandName = scanner.nextLine();

        System.out.print("New brand name: ");
        String newBrandName = scanner.nextLine();

        brandService.updateBrandName(newBrandName, oldBrandName);
    }

    public void updateBrandDescription() throws SQLException {
        System.out.print("Brand ID: ");
        int id = scanner.nextInt();

        System.out.println("New Brand description: ");
        String newDescription = scanner.nextLine();

        brandService.updateBrandDescription(newDescription, id);
    }

    public void loadBrand() throws SQLException {
        System.out.print("Enter Brand ID: ");
        int id = scanner.nextInt();

        brandService.load(id);
    }

    public void listAllBrands() throws SQLException {
        System.out.println();
        brandService.listAllBrands();
    }

    public void deleteBrand() throws SQLException {
        System.out.print("Enter Brand ID: ");
        int id = scanner.nextInt();

        brandService.delete(id);
    }


    public void shareholderMenu() throws SQLException {
        boolean shareholderMenuIsActive=true;
        while (shareholderMenuIsActive){
            System.out.println("+--------------------------------------------------+");
            System.out.println("|                HomeWork7-Maktab101               |");
            System.out.println("|      Product/Brand/Category Management system    |");
            System.out.println("|           Developed by: Sina Afzalsoltani        |");
            System.out.println("|                  SHARE-HOLDER MENU               |");
            System.out.println("+--------------------------------------------------+");
            System.out.println();
            System.out.println();
            System.out.println("Choose an option from the menu:");
            System.out.println("1- Create a new shareholder");
            System.out.println("2- Update shareholder's name");
            System.out.println("3- Update shareholder's phone number");
            System.out.println("4- Update shareholder's national code");
            System.out.println("5- Load a shareholder");
            System.out.println("6- View list of all shareholders");
            System.out.println("7- Delete a shareholder");
            System.out.println("8- exit");

            String input = scanner.next();

            switch (input) {
                case "1" -> createShareHolder();
                case "2" -> updateShareHolderName();
                case "3" -> updateShareHolderPhoneNumber();
                case "4" -> updateShareHolderNationalCode();
                case "5" -> loadShareHolder();
                case "6" -> listAllShareHolders();
                case "7" -> deleteShareHolder();
                case "8" -> shareholderMenuIsActive = false;
                default -> {
                    System.out.println("Invalid input!");
                    scanner.next();
                    System.out.println("**********  Press any key to continue   **********");
                    scanner.next();
                }
            }
        }
    }

    public void createShareHolder() throws SQLException {
        System.out.println("Shareholder name: ");
        String shareHolderName = scanner.nextLine();

        System.out.println("Shareholder's phone number: ");
        String phoneNumber = null;
        boolean invalidPhoneNumber = true;
        while (invalidPhoneNumber) {
            phoneNumber = scanner.nextLine();
            if (Validation.isPhoneNumberValid(phoneNumber)) {
                invalidPhoneNumber = false;
            } else {
                System.out.println("Enter a valid phone number!");
            }
        }

        System.out.println("Shareholder's national code: ");
        String shareHolderNationalCode = null;
        boolean invalidNationalCode = true;
        while (invalidNationalCode) {
            shareHolderNationalCode = scanner.nextLine();
            if (Validation.isNationalCodeValid(shareHolderNationalCode)) {
                invalidNationalCode = false;
            } else {
                System.out.println("Enter a valid national code!");
            }
        }

        ShareHolder shareHolder = new ShareHolder(null, shareHolderName, phoneNumber, shareHolderNationalCode, null);
        shareHolderService.save(shareHolder);
    }
    public void updateShareHolderName(){}
    public void updateShareHolderPhoneNumber(){}
    public void updateShareHolderNationalCode(){}
    public void loadShareHolder(){}
    public void listAllShareHolders(){}
    public void deleteShareHolder(){}

    public void productMenu() {
    }

}
