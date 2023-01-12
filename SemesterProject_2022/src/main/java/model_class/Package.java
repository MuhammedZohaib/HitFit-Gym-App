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

    public Package() {
    }

    public Package(String title, String amount) {
        this.title = title;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return Integer.parseInt(amount);
    }

    public String getDescription() {
        return description;
    }

    public int getPACKAGE_NO() {
        return PACKAGE_NO;
    }

}
