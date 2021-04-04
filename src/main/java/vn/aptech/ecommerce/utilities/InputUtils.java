package vn.aptech.ecommerce.utilities;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputUtils {

    public static final Scanner scanner = new Scanner(System.in);

    public static String inputString(String title) {
        System.out.print("\t" + title);
        String str = scanner.nextLine();
        return str;
    }

    public static Integer inputInteger(String title) {
        while (true) {
            System.out.print("\t" + title);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Sai dinh dang so, vui long nhap lai!");
                scanner.nextLine();
            }
        }
    }

    public static Integer inputQuantity(String title) {
        while (true) {
            System.out.print("\t" + title);
            try {
                Integer num = scanner.nextInt();
                if (num < 1) {
                    System.err.println("So luong san pham phai lon hon 0, vui long nhap lai!");
                    continue;
                }
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Sai dinh dang so, vui long nhap lai!");
                scanner.nextLine();
            }
        }
    }

    public static Integer inputDiscount(String title) {
        while (true) {
            System.out.print("\t" + title);
            try {
                Integer num = scanner.nextInt();
                if (num < 0 || num > 100) {
                    System.err.println("% giam gia phai lon hon 0 va nho hon 100, vui long nhap lai!");
                    continue;
                }
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Sai dinh dang so, vui long nhap lai!");
                scanner.nextLine();
            }
        }
    }

    public static Double inputDouble(String title) {
        System.out.print("\t" + title);
        Double number = scanner.nextDouble();
        return number;
    }

    public static BigDecimal inputBigDecimal(String title) {
        System.out.print(title);
        BigDecimal number = scanner.nextBigDecimal();
        return number;
    }

    public static Double inputPrice(String title) {
        while (true) {
            System.out.print("\t" + title);
            try {
                Double num = scanner.nextDouble();
                if (num < 0) {
                    System.err.println("Gia san pham khong duoc nho hon 0, vui long nhap lai!");
                    continue;
                }
                return num;
            } catch (InputMismatchException e) {
                System.err.println("Sai dinh dang so, vui long nhap lai!");
                scanner.nextLine();
            }
        }
    }

    public static String inputName(String title) {
        while (true) {
            System.out.print("\t" + title);
            try {
                String name = scanner.nextLine();
                if (name.length() <= 0) {
                    System.err.println("Ten san pham khong duoc de trong, vui long nhap lai!");
                    continue;
                }
                if (name.length() > 45) {
                    System.err.println("Do dai ten san pham khong vuot qua 45 ky tu, vui long nhap lai!");
                    continue;
                }
                return name;
            } catch (InputMismatchException e) {
                System.err.println("Sai dinh dang van ban, vui long nhap lai!");
                scanner.nextLine();
            }
        }
    }

    public static String inputDesc(String title) {
        while (true) {
            System.out.print("\t" + title);
            try {
                String desc = scanner.nextLine();
                if (desc.length() > 5000) {
                    System.err.println("Do dai mo ta san pham khong vuot qua 5000 ky tu, vui long nhap lai!");
                }
                return desc;
            } catch (InputMismatchException e) {
                System.err.println("Sai dinh dang van ban, vui long nhap lai!");
                scanner.nextLine();
            }
        }
    }

    public static void nextLine() {
        scanner.nextLine();
    }
}
