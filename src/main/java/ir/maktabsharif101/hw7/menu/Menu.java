package ir.maktabsharif101.hw7.menu;

import ir.maktabsharif101.hw7.service.*;

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

}
