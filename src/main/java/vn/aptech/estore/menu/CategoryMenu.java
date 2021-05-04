package vn.aptech.estore.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.service.CategoryService;
import vn.aptech.estore.service.CategoryServiceImpl;
import vn.aptech.estore.utilities.InputUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CategoryMenu extends BaseMenu {
    private final Logger LOGGER = LogManager.getLogger(CategoryMenu.class);
    private final DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
    private final Scanner scanner;
    private final CategoryService categoryService;

    public CategoryMenu() {
        this.categoryService = new CategoryServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void create() {
        displayTitle("Them danh muc moi");
        try {
            while (true) {
                scanner.nextLine();
                String name = InputUtils.inputString("Nhap ten danh muc: ");
                Optional<Category> category = categoryService.saveOrUpdate(new Category(name));
                if (category.isPresent()) {
                    show("Them danh muc moi thanh cong!");
                } else {
                    show("Them danh muc moi that bai!");
                }

                String choice = InputUtils.inputString("Ban muon them danh muc khac khong? (y/N): ");

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("create exception: ", e);
        }
    }

    @Override
    public void show() {
        displayTitle("Danh sach danh muc");
        try {
            List<Category> categories = categoryService.findAll();
            if (categories.size() < 1) {
                show("Danh sach danh muc trong!");
                show("Nhan <enter> de thoat.");
                scanner.nextLine();
            } else {
                System.out.println("ID\t\tNAME\t\tCREATE DATE");
                for (Category category : categories) {
                    System.out.printf("%d\t\t%s\t\t%s%n",
                            category.getId(),
                            category.getName(),
                            dateFormat.format(category.getCreatedDate())
                    );
                }
                show("Nhan <enter> de thoat.");
                scanner.nextLine();
            }
        } catch (SQLException e) {
            LOGGER.error("show exception", e);
        }

    }

    @Override
    public void edit() {
        displayTitle("Chinh sua danh muc");
        try {
            while (true) {
                Integer id = InputUtils.inputInteger("Nhap vao ID cua danh muc muon chinh sua: ");
                InputUtils.nextLine();

                Optional<Category> old = categoryService.findById(id);
                if (old.isPresent()) {
                    System.out.println("Ban dang sua danh muc: " + old.get().getName());
                    String name = InputUtils.inputString("Nhap ten danh muc: ");
                    old.get().setName(name);
                    old.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));

                    Optional<Category> category = categoryService.saveOrUpdate(old.get());
                    if (category.isPresent()) {
                        show("Cap nhat danh muc thanh cong!");
                    } else {
                        show("Cap nhat danh muc that bai!");
                    }
                } else {
                    show("Khong tim thay danh muc co ID = " + id);
                }

                String choice = InputUtils.inputString("Ban muon chinh sua danh muc khac khong? (y/N): ");

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }

        } catch (SQLException e) {
            LOGGER.error("edit exception", e);
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
                    String choice = InputUtils.inputString("Ban co muon xoa danh muc nay '" + category.get().getName() + "'? (y/N): ");
                    if ("y".equalsIgnoreCase(choice)) {
                        if (categoryService.deleteById(id)) {
                            show("Xoa danh muc thanh cong!");
                        } else {
                            show("Xoa danh muc that bai!");
                        }
                    }
                } else {
                    show("Khong tim thay danh muc co ID = " + id);
                }
                String choice = InputUtils.inputString("Ban co muon xoa danh muc khac khong? (y/N): ");

                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("delete exception: ", e);
        }
    }

    @Override
    public void start() {
        int userOption;
        do {
            this.printMenuHeader();
            userOption = InputUtils.inputInteger("Nhap lua chon [1-5]: ");

            switch (userOption) {
                case 1:
                    this.show();
                    break;
                case 2:
                    this.create();
                    break;
                case 3:
                    this.edit();
                    break;
                case 4:
                    this.delete();
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
        displayTitle("Danh muc");
        System.out.println("Vui long chon chuc nang sau do nhan <enter>: ");
        System.out.println("\t1. Danh sach tat ca danh muc");
        System.out.println("\t2. Them moi mot danh muc");
        System.out.println("\t3. Chinh sua mot danh muc");
        System.out.println("\t4. Xoa mot danh muc");
        System.out.println("\t5. Quay lai man hinh chinh");
    }
}
