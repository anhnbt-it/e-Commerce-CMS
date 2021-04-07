package vn.aptech.ecommerce.controller;

import vn.aptech.ecommerce.service.ImageService;
import vn.aptech.ecommerce.utilities.InputUtils;
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
import java.util.logging.Level;
import vn.aptech.ecommerce.constant.Constant;
import vn.aptech.ecommerce.service.ImageServiceImpl;

public class ProductController extends BaseController {

    private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

    DateFormat dateFormat = new SimpleDateFormat(Constant.DATE.FORMAT.DATE);

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    public ProductController() {
        this.productService = new ProductServiceImpl();
        this.categoryService = new CategoryServiceImpl();
        this.imageService = new ImageServiceImpl();
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
        Product product = this.stepOne();
        this.stepTwo(product);
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
                    showMessage("Xoa san pham thanh cong!");
                } else {
                    showMessage("Da xay ra loi!");
                }
            } else {
                showMessage("San pham khong ton tai.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
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

                if (productService.saveOrUpdate(product) != -1) {
                    showMessage("Them san pham moi thanh cong!");
                } else {
                    showMessage("Them san pham moi that bai!");
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
