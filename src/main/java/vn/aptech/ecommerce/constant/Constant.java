package vn.aptech.ecommerce.constant;

public interface Constant {
    interface USER {
        interface STATUS {
            int ACTIVE = 1;
            int INACTIVE = 0;
        }
    }

    interface DATE {
        interface FORMAT {
            String DATE_TIMESTAMP = "HH:mm:ss.SSS dd/MM/yyyy";
            String DATE_TIME = "HH:mm:ss dd/MM/yyyy";
            String DATE = "dd/MM/yyyy";
            String TIME = "HH:mm:ss";
        }
    }
}
