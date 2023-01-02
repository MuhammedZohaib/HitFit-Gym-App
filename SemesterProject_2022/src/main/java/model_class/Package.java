package model_class;

public class Package {

    private String title;
    private String amount;
    private String description;
    private int PACKAGE_NO;

    public Package(int PACKAGE_NO, String title, String amount, String description) {
        this.title = title;
        this.amount = amount;
        this.description = description;
        this.PACKAGE_NO = PACKAGE_NO;
    }
}
