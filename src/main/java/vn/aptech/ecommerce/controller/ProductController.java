package vn.aptech.ecommerce.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.ecommerce.entities.Category;
import vn.aptech.ecommerce.entities.Product;
import vn.aptech.ecommerce.service.CategoryService;
import vn.aptech.ecommerce.service.CategoryServiceImpl;
import vn.aptech.ecommerce.service.ProductService;
import vn.aptech.ecommerce.service.ProductServiceImpl;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


public class ProductController extends BaseController {
    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //hh:mm:ss

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserInputMethod userInputMethod;

    public ProductController() {
        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
        this.userInputMethod = new UserInputMethod();
    }

    private void showCategories() throws SQLException {
        List<Category> categories = categoryService.findAll();
        if (categories.size() > 0) {
            for (Category category : categories) {
                System.out.println(category.toString());
            }
        } else {
            System.out.println("Danh muc trong!");
        }
    }

    @Override
    public void create() {
        displayTitle("Them san pham moi");
        showMessage("Cac truong danh dau * la bat buoc phai nhap.");
//        this.stepOne();
        this.stepTwo();
    }

    @Override
    public void show() {
        displayTitle("Danh sach san pham");
        try {
            List<Product> products = productService.findAll();
            if (products.size() < 1) {
                showMessage("Danh sach san pham trong!");
            } else {
                System.out.println("ID\tTEN\tGIA\t\tGIA GOC\t\tDISCOUNT");
                DecimalFormat formatter = new DecimalFormat("###,###,###");
//                Locale loc = Locale.getDefault();
//                NumberFormat nf = NumberFormat.getCurrencyInstance(loc);
//                nf.format(1000000)); // Output: $1,000,000.00
                for (Product product : products) {
                    Optional<Category> category = categoryService.findById(product.getCategoryId());
                    System.out.printf("%d\t" // Id
                            + "%s\t" // Ten SP
                            + "%s\t" // Gia cu
                            + "%s\t" // Giam gia
                            + "%s\t" // Gia moi
                            + "%n", product.getId(),
                            product.getName(),
                            formatter.format(product.getUnitPrice()) + " VND",
                            product.getDiscount() + "%",
                            formatter.format(product.getNewPrice()) + " VND");
                }
                showMessage("Nhan <enter> de thoat.");
                userInputMethod.nextLine();
            }
        } catch (SQLException e) {
            LOGGER.error("Exception when show product: ", e);
        }
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void stepOne() {
        String choice;
        try {
            do {
                displayTitle("Buoc 1: Thong tin co ban");
                String name = userInputMethod.inputName("Nhap ten san pham [Toi da 45 ky tu] (*): ");
                Double price = userInputMethod.inputPrice("Nhap gia cua san pham [VND] (*): ");
                Integer discount = 0;
                if (price > 0) {
                    discount = userInputMethod.inputDiscount("Nhap % giam gia [0-100]: ");
                }
                Integer quantity = userInputMethod.inputQuantity("Nhap so luong cua san pham (*): ");
                userInputMethod.nextLine();
                String thumbnailUrl = userInputMethod.inputString("Nhap duong dan anh cua san pham (*): ");
                String description = userInputMethod.inputDesc("Nhap mo ta cua san pham [Toi da 5000 ky tu]: ");

                System.out.println("Chon danh muc cho san pham");
                choice = userInputMethod.inputString("Ban co muon xem danh sach danh muc khong? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    showCategories();
                }
                Integer categoryId = userInputMethod.inputInteger("Nhap ID danh muc muon chon (*): ");

                System.out.println("Chon thuong hieu cho san pham");
                userInputMethod.nextLine();
                choice = userInputMethod.inputString("Ban co muon xem danh sach thuong hieu khong? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    showCategories();
                }
                Integer brandId = userInputMethod.inputInteger("Nhap ID thuong hieu muon chon (*): ");

                System.out.println("Chon nha cung cap cho san pham");
                userInputMethod.nextLine();
                choice = userInputMethod.inputString("Ban co muon xem danh sach nha cung cap khong? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    showCategories();
                }
                Integer supplierId = userInputMethod.inputInteger("Nhap ID nha cung cap muon chon (*): ");
                userInputMethod.nextLine();
                Product product = new Product();
                product.setCategoryId(categoryId);
                product.setBrandId(brandId);
                product.setSupplierId(supplierId);
                product.setName(name);
                product.setUnitPrice(price);
                product.setDiscount(discount);
                product.setThumbnailUrl(thumbnailUrl);
                product.setDescription(description);
                product.setQuantity(quantity);

                if (productService.saveOrUpdate(product) != -1) {
                    showMessage("Them san pham moi thanh cong!");
                } else {
                    showMessage("Them san pham moi that bai!");
                }

                choice = userInputMethod.inputString("Ban muon them san pham khac khong? (y/N): ");
            } while ("y".equalsIgnoreCase(choice));
        } catch (SQLException e) {
            LOGGER.error("Exception when add product step 1: ", e);
        }
    }

    private void stepTwo() {
        displayTitle("Buoc 2: Nhap anh cho san pham");
        do {
            String targetFile = userInputMethod.inputString("Nhap duong dan den tap tin anh trong may: ");
            copyFile(targetFile, "D:\\uploads");
        } while (true);
    }
    
    public static void copyFile(String filePath, String dir) {
        Path sourceFile = Paths.get(filePath);
        Path targetDir = Paths.get(dir);
        Path targetFile = targetDir.resolve(sourceFile.getFileName());
        try {
            Files.copy(sourceFile, targetFile);
        } catch (FileAlreadyExistsException ex) {
            System.err.format("File %s already exists.", targetFile);
        } catch (IOException ex) {
            System.err.format("I/O Error when copying file");
        }
    }
}
