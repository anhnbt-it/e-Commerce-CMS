package vn.aptech.estore.utilities;

import lombok.Data;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: anhnb
 * Date: 5/7/2021
 * Time: 8:26 AM
 */
@Data
public class ResourceBundlesUtils {

    private Locale[] supportedLocales = {
            new Locale("en", "US"),
            new Locale("vi", "VN")
    };

    public ResourceBundlesUtils() {
    }

    public static ResourceBundle getBundle() {
        Locale currentLocale = new Locale("en", "US");
        return ResourceBundle.getBundle("messages", currentLocale);
    }
}
