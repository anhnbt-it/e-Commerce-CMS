package vn.aptech.estore.menu;

import vn.aptech.estore.service.ImageService;
import vn.aptech.estore.utilities.InputUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.service.CategoryService;
import vn.aptech.estore.service.CategoryServiceImpl;
import vn.aptech.estore.service.ProductService;
import vn.aptech.estore.service.ProductServiceImpl;

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
import java.util.ResourceBundle;
import java.util.Scanner;

import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.service.ImageServiceImpl;

public class ProductMenu extends BaseMenu {

    private static final Logger LOGGER = LogManager.getLogger(ProductMenu.class);

    DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    private final Scanner scanner;
    private final ResourceBundle messages;

    public ProductMenu(Scanner scanner, ResourceBundle messages) {
        this.scanner = scanner;
        this.messages = messages;

        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
        this.imageService = new ImageServiceImpl();
    }

    private void showCategories() throws SQLException {
        List<Category> categories = categoryService.findAll();
        if (categories.size() > 0) {
            categories.forEach((Category category) -> {
                System.out.println(category.toString());
            });
        } else {
            System.out.println("Danh muc trong!");
        }
    }

    @Override
    public void create() {
        displayTitle("Them san pham moi");
        show("Cac truong danh dau * la bat buoc phai nhap.");
        Product product = this.stepOne();
        this.stepTwo(product);
    }

    @Override
    public void show() {
        displayTitle("Danh sach san pham");
        try {
            List<Product> products = productService.findAll();
            if (products.size() < 1) {
                show("Danh sach san pham trong!");
            } else {
                System.out.println("ID\tTEN\tGIA\t\tGIA GOC\t\tDISCOUNT");
                DecimalFormat formatter = new DecimalFormat("###,###,###");
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
                show("Nhan <enter> de thoat.");
                InputUtils.nextLine();
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
        displayTitle("Xoa san pham");
        Integer productId = InputUtils.inputInteger("Nhap ID san pham muon xoa: ");
        try {
            if (productService.existById(productId)) {
                if (productService.deleteById(productId)) {
                    show("Xoa san pham thanh cong!");
                } else {
                    show("Da xay ra loi!");
                }
            } else {
                show("San pham khong ton tai.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void start() {
        int userOption;
        do {
            printMenuHeader();
            userOption = Integer.parseInt(InputUtils.inputString("Nhap lua chon [1-5]: "));
            switch (userOption) {
                case 1:
                    this.show();
                    break;
                case 2:
                    this.create();
                    break;
                case 3:
//                    productController.edit(scanner);
                    break;
                case 4:
//                    productController.delete(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lua chon khong ton tai.\n\n");
            }
        } while (true);
    }

    @Override
    public void printMenuHeader() {
        displayTitle("San pham");
        System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
        System.out.println("\t1. Danh sach tat ca san pham");
        System.out.println("\t2. Them moi mot san pham");
        System.out.println("\t3. Chinh sua mot san pham");
        System.out.println("\t4. Xoa mot san pham");
        System.out.println("\t5. Quay lai man hinh chinh");
    }

    private Product stepOne() {
        String choice;
        Product product = null;
        try {
            do {
                displayTitle("Buoc 1: Thong tin co ban");
                String name = InputUtils.inputName("Nhap ten san pham [Toi da 45 ky tu] (*): ");
                Double price = InputUtils.inputPrice("Nhap gia cua san pham [VND] (*): ");
                Integer discount = 0;
                if (price > 0) {
                    discount = InputUtils.inputDiscount("Nhap % giam gia [0-100]: ");
                }
                Integer quantity = InputUtils.inputQuantity("Nhap so luong cua san pham (*): ");
                InputUtils.nextLine();
                String thumbnailUrl = InputUtils.inputString("Nhap duong dan anh cua san pham (*): ");
                String description = InputUtils.inputDesc("Nhap mo ta cua san pham [Toi da 5000 ky tu]: ");

                System.out.println("Chon danh muc cho san pham");
                choice = InputUtils.inputString("Ban co muon xem danh sach danh muc khong? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    showCategories();
                }
                Integer categoryId = InputUtils.inputInteger("Nhap ID danh muc muon chon (*): ");

                System.out.println("Chon thuong hieu cho san pham");
                InputUtils.nextLine();
                choice = InputUtils.inputString("Ban co muon xem danh sach thuong hieu khong? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    showCategories();
                }
                Integer brandId = InputUtils.inputInteger("Nhap ID thuong hieu muon chon (*): ");

                System.out.println("Chon nha cung cap cho san pham");
                InputUtils.nextLine();
                choice = InputUtils.inputString("Ban co muon xem danh sach nha cung cap khong? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    showCategories();
                }
                Integer supplierId = InputUtils.inputInteger("Nhap ID nha cung cap muon chon (*): ");
                InputUtils.nextLine();
                product = new Product();
                product.setCategoryId(categoryId);
                product.setBrandId(brandId);
                product.setSupplierId(supplierId);
                product.setName(name);
                product.setUnitPrice(price);
                product.setDiscount(discount);
                product.setThumbnailUrl(thumbnailUrl);
                product.setDescription(description);
                product.setQuantity(quantity);

                Optional<Product> createProduct = productService.saveOrUpdate(product);

                if (createProduct.isPresent()) {
                    show("Them san pham moi thanh cong!");
                } else {
                    show("Them san pham moi that bai!");
                }

                choice = InputUtils.inputString("Ban muon them san pham khac khong? (y/N): ");
            } while ("y".equalsIgnoreCase(choice));
        } catch (SQLException e) {
            LOGGER.error("Exception when add product step 1: ", e);
        }
        return product;
    }

    private void stepTwo(Product product) {
        String choice;
        do {
            displayTitle("Buoc 2: Nhap anh cho san pham");
            String targetFile = InputUtils.inputString("Nhap duong dan den tap tin anh trong may: ");
            Path target = copyFile(targetFile, "D:\\uploads");
            if (target.isAbsolute()) {
                imageService.save(target.getFileName().toString(), product.getId());
            }
            choice = InputUtils.inputString("Ban co muon them anh khac khong? (y/N): ");
        } while ("y".equalsIgnoreCase(choice));
    }

    private void stepThree() {
        displayTitle("Buoc 3: Chon danh muc san pham");
    }

    private Path copyFile(String filePath, String dir) {
        Path sourceFile = Paths.get(filePath);
        Path targetDir = Paths.get(dir);
        Path targetFile = targetDir.resolve(sourceFile.getFileName());
        try {
            Path target = Files.copy(sourceFile, targetFile);
        } catch (FileAlreadyExistsException e) {
            System.err.format("File '%s' da ton tai.\n", targetFile);
        } catch (IOException e) {
            System.err.format("Loi khi sao chep tep '%s'.\n", targetFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return targetFile;
    }
}
