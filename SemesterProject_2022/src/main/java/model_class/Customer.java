    package model_class;

    import all_enums.TimingSlot;
    import com.example.semesterProject_2022.CustomMenuButton;
    import com.example.semesterProject_2022.MembersDetailCard_Controller;
    import com.example.semesterProject_2022.MembersPanel_Controller;
    import database.DatabaseFunctions;
    import javafx.collections.FXCollections;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.paint.Paint;
    import org.jetbrains.annotations.NotNull;

    import java.io.IOException;
    import java.net.URL;
    import java.time.LocalDate;
    import java.util.ResourceBundle;

    public class Customer extends Person implements Comparable{


        private String address;
        private String dob;
        private String weight;
        private int monthlyPlan;
        private Package monthlyPackage;
        private int customerId;
        private boolean isActive;
        private String passwordSalt;
        private String Fullname;
        private BMI CustomerBMI;
        private Boolean AdminAcces;
        private int Id;

        public String getFullname() {
            return Fullname;
        }

        public void setFullname(String fullname) {
            Fullname = fullname;
        }

        /*--------*/
        private CustomMenuButton actionBtn;

        private MenuItem item1 = new MenuItem("View");
        private MenuItem item2 = new MenuItem("Remove");
        /**/


        public CustomMenuButton getActionBtn() {
            return actionBtn;
        }

        public void setActionBtn(CustomMenuButton actionBtn) {
            this.actionBtn = actionBtn;
        }

        public Customer(Boolean AdminAccess, int Id,String firstName, String lastName, String email, String phoneNumber,String nicNumber, int monthlyPlan, CustomMenuButton customMenuButton) {
            super(firstName, lastName, email, "gender", phoneNumber, "userName", "password", nicNumber);
            this.Id = Id;
            this.monthlyPlan = monthlyPlan;
            Fullname=firstName+lastName;
            this.AdminAcces=AdminAccess;


            /*Action Button Stuff*/
            this.actionBtn = customMenuButton;
            this.actionBtn.setStyle("-fx-background-color: #00C2FF; -fx-background-radius: 12px;");
            this.actionBtn.setTextFill(Paint.valueOf("White"));
            if(this.AdminAcces==true)
            {
                actionBtn.getItems().addAll(item1,item2);
            } else
            {
                actionBtn.getItems().addAll(item1);
            }

            item1.setOnAction(event ->
            {
                MembersDetailCard_Controller.FullName = actionBtn.getFullName();
                MembersDetailCard_Controller.Weight = actionBtn.getWeight();
                MembersDetailCard_Controller.Address = actionBtn.getAddress();
                MembersDetailCard_Controller.Emails = actionBtn.getEmail();
                MembersDetailCard_Controller.Username = actionBtn.getUsername();
                MembersDetailCard_Controller.PackagePrice = actionBtn.getPackagePrice();
                MembersDetailCard_Controller.PackageType = actionBtn.getPackageType();

                try {
                    new MembersPanel_Controller().view();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            item2.setOnAction(event ->
            {

              MembersPanel_Controller.deletingId=actionBtn.getButtonId();
              DatabaseFunctions.deleteData("customers", MembersPanel_Controller.deletingId);
            }) ;





        }


        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public Customer(String firstName, String lastName, String email, String gender, String phoneNumber, String userName, String password, String nicNumber, String address, String dob, String weight, int monthlyPlan, int customerId, String passwordSalt) {
            super(firstName, lastName, email, gender, phoneNumber, userName, password, nicNumber);
            this.address = address;
            this.dob = dob;
            this.weight = weight;
            this.monthlyPlan = monthlyPlan;
            this.customerId = customerId;
            this.passwordSalt = passwordSalt;
        }

        public Package getMonthlyPackage() {
            return monthlyPackage;
        }

        public void setMonthlyPackage(Package monthlyPackage) {
            this.monthlyPackage = monthlyPackage;
        }

        public Customer(){
            super();
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public int getMonthlyPlan() {
            return monthlyPlan;
        }

        public void setMonthlyPlan(int monthlyPlan) {
            this.monthlyPlan = monthlyPlan;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public String getPasswordSalt() {
            return passwordSalt;
        }

        public void setPasswordSalt(String passwordSalt) {
            this.passwordSalt = passwordSalt;
        }
        public String tolowerfirstname()
        {
            return getFirstName().toLowerCase();
        }
        @Override
        public String toString() {
            return super.toString() +"Customer{" +
                    "address='" + address + '\'' +
                    ", dob='" + dob + '\'' +
                    ", weight='" + weight + '\'' +
                    ", monthlyPlan=" + monthlyPlan +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
