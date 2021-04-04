package vn.aptech.ecommerce.controller;

import vn.aptech.ecommerce.utilities.InputUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.ecommerce.entities.Category;
import vn.aptech.ecommerce.service.CategoryService;
import vn.aptech.ecommerce.service.CategoryServiceImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import vn.aptech.ecommerce.constant.Constant;

public class CategoryController extends BaseController {
    
    DateFormat dateFormat = new SimpleDateFormat(Constant.DATE.FORMAT.DATE);
    Logger logger = LogManager.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController() {
        this.categoryService = new CategoryServiceImpl();
    }

    @Override
    public void create() {
        displayTitle("Them danh muc moi");
        try {
            while (true) {
                String name = InputUtils.inputString("Nhap ten danh muc: ");
                Category category = new Category(name);
                if (categoryService.saveOrUpdate(category) != -1) {
                    showMessage("Them danh muc moi thanh cong!");
                } else {
                    showMessage("Them danh muc moi that bai!");
                }
                
                String choice = InputUtils.inputString("Ban muon them danh muc khac khong? (y/N): ");

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void show() {
        displayTitle("Danh sach danh muc");
        try {
            List<Category> categories = categoryService.findAll();
            if (categories.size() < 1) {
                showMessage("Danh sach danh muc trong!");
            } else {
                System.out.println("ID\t\tNAME\t\tCREATE DATE");
                for (Category category : categories) {
                    System.out.printf("%d\t\t%s\t\t%s%n", category.getId(), category.getName(), dateFormat.format(category.getCreatedAt()));
                }
                showMessage("Nhan <enter> de thoat.");
                InputUtils.nextLine();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

    }

    @Override
    public void edit() {
        displayTitle("Chinh sua danh muc");
        try {
            while (true) {
                Integer id = InputUtils.inputInteger("Nhap vao ID cua danh muc muon chinh sua: ");
                InputUtils.nextLine();

                Optional<Category> category = categoryService.findById(id);
                if (category.isPresent() && category.get().getId() != null) {
                    System.out.println("Ban dang sua danh muc: " + category.get().getName());
                    String name = InputUtils.inputString("Nhap ten danh muc: ");
                    category.get().setName(name);
                    category.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    if (categoryService.saveOrUpdate(category.get()) != -1) {
                        showMessage("Cap nhat danh muc thanh cong!");
                    } else {
                        showMessage("Cap nhat danh muc that bai!");
                    }
                } else {
                    showMessage("Khong tim thay danh muc co ID = " + id);
                }
                
                String choice = InputUtils.inputString("Ban muon chinh sua danh muc khac khong? (y/N): ");

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void delete() {
        displayTitle("Xoa danh muc");
        try {
            while (true) {
                Integer id = InputUtils.inputInteger("Nhap vao ID cua danh muc muon xoa: ");
                InputUtils.nextLine();
                Optional<Category> category = categoryService.findById(id);
                if (category.isPresent() && category.get().getId() != null) {
                    String choice = InputUtils.inputString("Ban co muon xoa danh muc nay '" + category.get().getName() + "?' (y/N): ");
                    if ("y".equalsIgnoreCase(choice)) {
                        if (categoryService.deleteById(id)) {
                            showMessage("Xoa danh muc thanh cong!");
                        } else {
                            showMessage("Xoa danh muc that bai!");
                        }
                    }
                } else {
                    showMessage("Khong tim thay danh muc co ID = " + id);
                }
                String choice = InputUtils.inputString("Ban co muon xoa danh muc khac khong? (y/N): ");

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
