package model_class;

public class Package2 extends Package{
    public Package2(int PACKAGE_NO, String title, String amount, String description) {
        super(PACKAGE_NO,"Starter Plan", "3000", description);
    }

    public Package2() {
        super("Starter Plan", "3000");
    }
}
