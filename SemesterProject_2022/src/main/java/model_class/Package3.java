package model_class;

public class Package3 extends Package{

    public Package3(int PACKAGE_NO, String description) {
        super(PACKAGE_NO, "Pro Plan", "4500", description);
    }

    public Package3() {
        super("Pro Plan", "4500");
    }
}
