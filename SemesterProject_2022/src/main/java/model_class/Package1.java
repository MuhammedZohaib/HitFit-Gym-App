package model_class;

public class Package1 extends Package{
    public Package1(int PACKAGE_NO, String description) {
        super(PACKAGE_NO, "Beginner Plan", "2000", description);
    }

    public Package1() {
        super("Beginner Plan", "2000");
    }
}
